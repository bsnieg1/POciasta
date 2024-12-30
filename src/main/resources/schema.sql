CREATE TABLE recipe (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);

CREATE TABLE ingredient (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            quantity VARCHAR(255),
                            recipe_id BIGINT,
                            CONSTRAINT fk_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(id)
);
