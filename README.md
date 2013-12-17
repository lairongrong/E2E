E2E
===
Prerequisit:
1. Tomcat 7.0
2. Java JDK 1.6
3. MySQL Community Server 5.6.15 
4. MySQL: user = root & password = 1234

Set Up:

1. Create a MySQL database name "NPro".
2. Execute NPro.sql to create and populate tablea:

	mysql NPro -uroot < NPro.sql
	
3. Create a few users in npro database.

INSERT INTO `NPro`.`User`
(`UID`,
`UserName`,
`Pwd`,
`RoleID`,
`ContactID`,
`ActiveStatus`,
`CreateDate`,
`CreateBy`,
`LastModifiedDate`,
`LastModifiedBy`)
VALUES
(1,
'Hao Wang',
'1234',
2,
1,
1,
'2013-12-15 12:00:00',
'HaoWang',
'2013-12-15 12:00:00',
'HaoWang'),
(2,
'Anthony Wang',
'1234',
2,
2,
1,
'2013-12-15 12:00:00',
'HaoWang',
'2013-12-15 12:00:00',
'HaoWang'),
(3,
'Derek Wang',
'1234',
2,
3,
1,
'2013-12-15 12:00:00',
'HaoWang',
'2013-12-15 12:00:00',
'HaoWang');


4. Import the Dynamic Web Project in Eclipse.
5. Locate NPro.properties and make sure the JDBC connection string matches your database configuration
6. Run the project.
