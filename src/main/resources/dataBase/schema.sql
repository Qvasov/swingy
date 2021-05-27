CREATE TABLE HEROES (
    f_name VARCHAR(20) NOT NULL primary key,
    f_class VARCHAR(255) NOT NULL,
    f_level INT NOT NULL,
    f_experience INT NOT NULL,
    f_hp INT NOT NULL,
    f_min_atk INT NOT NULL,
    f_max_atk INT NOT NULL,
    f_defence INT NOT NULL,
    f_weapon VARCHAR(255),
    f_w_stat INT,
    f_armor VARCHAR(255),
    f_a_stat INT,
    f_helm VARCHAR(255),
    f_h_stat INT
);