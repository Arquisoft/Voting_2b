package es.uniovi.asw.voterResult;

import java.util.Timer;
import java.util.TimerTask;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.Vote;
import es.uniovi.asw.dbManagement.persistence.Repository;

public class TimerBBDD {

	public static boolean exist = false;

	public static void mantenimientoBBDD() {
		Timer timer;
		timer = new Timer();
		TimerTask task = null;
		if (!exist) {
			task = new TimerTask() {

				@Override
				public void run() {

					System.out.println("Mantenimiento BBDD");

					Election e = Repository.electionR.findByName("Independencia Escocia");

					for (Vote v : e.getVotes()) {
						if (v.isReadyToRecount()) {
							v.setReadyToRecount(false);
						}
						Repository.voteR.save(v);
					}

				}
			};
			// Empieza en 0ms y se lanza la tarea cada 2000ms
			timer.schedule(task, 200000, 200000);
		}
		exist=true;
	}
}