
-- -- -- -- tables
-- -- -- Table: Group
-- CREATE TABLE "Group" (
--     group_id serial NOT NULL,
--    group_name varchar(20) UNIQUE NOT NULL,
--     description varchar(250)  NOT NULL,
--     CONSTRAINT Group_pk PRIMARY KEY (group_id)
-- );

-- -- Table: User
-- CREATE TABLE "User" (
--     user_login varchar(20)  NOT NULL,
--     password varchar(20)  NOT NULL,
--     CONSTRAINT User_pk PRIMARY KEY (user_login)
-- );

-- -- Table: groups_users
-- CREATE TABLE groups_users (
--     group_id int  NOT NULL,
--     user_login varchar(20)  NOT NULL,
--     CONSTRAINT groups_users_pk PRIMARY KEY (group_id,user_login)
-- );

-- -- -- foreign keys
-- -- -- Reference: groups_users_Group (table: groups_users)
-- -- ALTER TABLE groups_users ADD CONSTRAINT groups_users_Group
-- --     FOREIGN KEY (group_id)
-- --     REFERENCES "Group" (group_id)  
-- --     NOT DEFERRABLE 
-- --     INITIALLY IMMEDIATE
-- -- ;

-- -- -- Reference: groups_users_User (table: groups_users)
-- -- ALTER TABLE groups_users ADD CONSTRAINT groups_users_User
-- --     FOREIGN KEY (user_login)
-- --     REFERENCES "User" (user_login)  
-- --     NOT DEFERRABLE 
-- --     INITIALLY IMMEDIATE
-- -- ;

-- -- -- -- End of file.


-- drop table groups_users;
-- DROP table "Group";
-- drop table "User";

-- -- -- INSERT INTO "User" (user_login ,password) VALUES ('dimas' , '1234');
-- INSERT INTO "Group" (group_name,description) VALUES ('dimas' , '1234');

-- -- -- SELECT * from "User";
-- SELECT * from "Group";
-- -- select count(1) from "Group";
-- DELETE FROM "Group" where group_id = 2;   

-- -- SELECT user_id from account where username LIKE 'dima';

-- INSERT INTO "User" (user_login,password) VALUES ('dima' , '1234');
-- SELECT * FROM "User";

-- DELETE FROM "User" WHERE user_login LIKE 'dima';

-- -- Select from "User" where user_login LIKE 'dima';

-- SELECT * from "User";

-- INSERT INTO "Group" (group_name,description) VALUES ('dimas' , '1234');
-- UPDATE "Group" SET group_name = 'name_2', description = 'description_1' WHERE group_id = 60;
-- DELETE FROM "Group" WHERE group_id = 60;



-- SELECT group_id, group_name, description FROM "Group" WHERE group_id = 91;
-- DELETE FROM "Group" WHERE group_id = 91;
