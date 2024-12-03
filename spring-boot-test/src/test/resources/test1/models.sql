CREATE TABLE models (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    model_archive BINARY LARGE OBJECT
);

INSERT INTO models(name, model_archive) VALUES ( 'model1', FILE_READ('classpath:test1/test-1.zip'));
INSERT INTO models(name) VALUES ( 'model2' );
INSERT INTO models(name) VALUES ( 'model3' );
INSERT INTO models(name) VALUES ( 'model4' );
INSERT INTO models(name) VALUES ( 'model5' );