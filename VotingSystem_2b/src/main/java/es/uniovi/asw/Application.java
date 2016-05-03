package es.uniovi.asw;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import es.uniovi.asw.model.User;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.faces.config.ConfigureListener;

import es.uniovi.asw.configuration.ViewScope;
import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.electionday.BeanConfigElection;
import es.uniovi.asw.electionday.BeanPollingPlaces;
import es.uniovi.asw.electionday.InsertElectoralListsR;
import es.uniovi.asw.electionday.parser.RRegionExcel;
import es.uniovi.asw.model.Voter;

@Controller
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"es.uniovi.asw"})
public class Application extends SpringBootServletInitializer implements ServletContextAware {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);

        System.out.println(Repository.voterR.count());
        new InsertElectoralListsR().insertRegions(new RRegionExcel().read("src/main/test/regiones.xlsx"));
       // new InsertRCandidature().insert(new RCandidatureExcel().read("src/main/test/candidatures.xlsx"));
      //  new InsertRCandidate().insert(new RCandidateExcel().read("src/main/test/candidatos.xlsx"));


        User junta = Repository.userR.findByEmailAndPassword("junta","junta");
        if(junta==null){
            User user = new User("junta","junta","junta",1,"junta");
            Repository.userR.save(user);
        }

        Voter voter = new Voter();
        voter.setName("Labra");
        voter.setNif("labra");
        voter.setEmail("labra@uniovi.es");
        voter.setPassword("labra");
        voter.setPollingPlace(Repository.pollingPlaceR.findOne((long) 9001));
        try {
    		Repository.voterR.save(voter);
    	} catch (DataIntegrityViolationException e) {}

    }

    @Bean
    public ServletRegistrationBean facesServletRegistraiton() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
        registration.setName("Faces Servlet");
        registration.setLoadOnStartup(1);
        return registration;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
            servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
        };
    }



    @RequestMapping(method = RequestMethod.POST, value = "/uploadPolling")
    public String handleExcelUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

       return handleFileUpload(file,redirectAttributes,true);



    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,boolean isPolling) {


        String name ="elecciones.xlsx";

        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
            return redirecIfPolling(isPolling);
        }
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
            return redirecIfPolling(isPolling);
        }

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File( "src/main/test/resources" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + name + "!");
            }
            catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload " + name + " => " + e.getMessage());
            }
        }
        else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload " + name + " because the file was empty");
        }

        if(isPolling){
            BeanPollingPlaces.setExcelUploaded(true);
            return redirecIfPolling(isPolling);

        }
        else {

            BeanConfigElection.setExcelUploaded(true);
            return redirecIfPolling(isPolling);

        }

    }


    private String redirecIfPolling(boolean isPolling){

        if(!isPolling){
            return "redirect:/selectElection.xhtml";

        }
        else {

            return "redirect:/configuratePollingPlaces.xhtml";

        }

    }



    @Bean
    public static CustomScopeConfigurer customScopeConfigurer(){
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        Map<String, Object> scopes = new HashMap<String, Object>();
        scopes.put("view", new ViewScope());
        configurer.setScopes(scopes);
        return configurer;
    }
    
    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName(
                        "redirect:/index.xhtml");
            }
        };
    }

    @Bean
    public ServletRegistrationBean facesServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new FacesServlet(), "*.xhtml");
        registration.setLoadOnStartup(1);
        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<ConfigureListener>(
                new ConfigureListener());
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

    }

}