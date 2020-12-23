create table HH_Vacancy (
	uuid_ VARCHAR(75) null,
	vacancyId LONG not null primary key,
	vacancyName VARCHAR(75) null,
	employerName VARCHAR(75) null,
	createdAt VARCHAR(75) null,
	salaryFrom INTEGER,
	salaryTo INTEGER,
	salaryGross BOOLEAN,
	salaryCurrency VARCHAR(75) null
);