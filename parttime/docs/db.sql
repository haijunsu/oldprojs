DROP table tbl_record;
DROP table tbl_dataset;
DROP table tbl_sensor;
DROP table tbl_rawfile;
DROP table tbl_bpl_rowfile;

CREATE TABLE tbl_sensor(
    id INT NOT NULL AUTO_INCREMENT,
    sensor_name VARCHAR(100) NOT NULL,
    sensor_type VARCHAR(40),
    floor VARCHAR(20),
    device_id VARCHAR(20),
    building VARCHAR(80),
    campus VARCHAR(50),
    dataset_summary VARCHAR(250),
    intervals VARCHAR(20),
    record_count INT,
    start_time TIMESTAMP NULL,
    end_time TIMESTAMP NULL,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY ( id ),
    CONSTRAINT uc_sensor_name UNIQUE (sensor_name)
 );

CREATE UNIQUE INDEX index_uc_sensor_name
ON tbl_sensor (sensor_name);


CREATE TABLE tbl_dataset(
    id INT NOT NULL AUTO_INCREMENT,
	sensor_id INT NOT NULL,
	raw_file VARCHAR(250),
	start_time TIMESTAMP NULL,
	end_time TIMESTAMP NULL,
	record_interval INT,
	record_count INT,
	notes VARCHAR(250),
	report_timings VARCHAR(30),
	import_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT fk_sensor FOREIGN KEY (sensor_id) REFERENCES tbl_sensor(id)
);
CREATE INDEX index_dataset_start_time ON tbl_dataset (start_time);
CREATE INDEX index_dataset_lend_time ON tbl_dataset (end_time);

CREATE TABLE tbl_record(
    id INT NOT NULL AUTO_INCREMENT,
	sensor_id INT NOT NULL,
	dataset_id INT NOT NULL,
	record_interval INT,
	record_time TIMESTAMP NOT NULL,
	value DECIMAL(9,2),
	notes VARCHAR(250),
	PRIMARY KEY (id),
	CONSTRAINT fk_sensor_record FOREIGN KEY (sensor_id) REFERENCES tbl_sensor(id),
	CONSTRAINT fk_dataset_record FOREIGN KEY (dataset_id) REFERENCES tbl_dataset(id)
);
CREATE INDEX index_record_time ON tbl_record (record_time);

CREATE TABLE tbl_rawfile (
    id INT NOT NULL AUTO_INCREMENT,
    pbl_rawfile_id INT NOT NULL,
	file_name VARCHAR(250),
	file_size INT,
	dataset_count INT,
	sensor_count INT,
	record_count INT,
	building VARCHAR(80),
	campus VARCHAR(50),
	status VARCHAR(20),
	notes VARCHAR(256),
	imported_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT fk_pbl_rawfile_id FOREIGN KEY (pbl_rawfile_id) REFERENCES tbl_pbl_rawfile(id)
);
CREATE UNIQUE INDEX index_rawfile_name ON tbl_rawfile (file_name);

CREATE TABLE tbl_pbl_rawfile (
    id INT NOT NULL AUTO_INCREMENT,
    pbl_id INT NOT NULL,
    directory VARCHAR(250),
	file_name VARCHAR(250),
	file_size INT,
	dataset_count INT,
	building VARCHAR(80),
	campus VARCHAR(50),
	status VARCHAR(20),
	notes VARCHAR(256),
	entered_time TIMESTAMP,
	downloaded_time TIMESTAMP,
	imported_time TIMESTAMP,
	PRIMARY KEY (id)
);
CREATE UNIQUE INDEX index_file_name ON tbl_pbl_rawfile (file_name);