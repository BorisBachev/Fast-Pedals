ALTER TABLE user
    DROP COLUMN `role`;

ALTER TABLE user
    ADD `role` ENUM('USER', 'ADMIN') NULL;