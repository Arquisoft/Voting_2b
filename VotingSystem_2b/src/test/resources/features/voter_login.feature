Feature: VotersManagement
	Scenario: Voter login
		Given I'm at /index.xhtml
		When I click on the button to sign up
		And I fill the DNI field with "labra"
		And I fill the Password Field with "labra"
		And I click the login button
		Then I can see the vote button