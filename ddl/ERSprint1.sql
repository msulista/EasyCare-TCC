CREATE TABLE paciente (
  paci_id BIGINT NOT NULL AUTO_INCREMENT,
  paci_nome VARCHAR(150) NOT NULL,
  paci_dt_nascimento DATE NOT NULL,
  paci_fone VARCHAR(10) NOT NULL,
  paci_no_familiar VARCHAR(150) NULL,
  paci_fone_familiar VARCHAR(10) NULL,
  paci_endereco VARCHAR(250) NOT NULL,
  paci_freq_hidra NUMERIC(2) NULL,
  paci_email_familiar VARCHAR(100) NULL,
  PRIMARY KEY(paci_id)
);

CREATE TABLE cuidador (
  cuid_id BIGINT NOT NULL AUTO_INCREMENT,
  cuid_nome VARCHAR(150) NOT NULL,
  cuid_fone VARCHAR(10) NULL,
  cuid_dt_nascimento DATE NULL,
  cuid_email VARCHAR(100) NOT NULL,
  cuid_descricao VARCHAR(400) NULL,
  cuid_anuncio VARCHAR(500) NULL,
  PRIMARY KEY(cuid_id)
);

CREATE TABLE medicamento (
  medi_id BIGINT NOT NULL AUTO_INCREMENT,
  paci_id BIGINT NULL,
  medi_nome VARCHAR(150) NULL,
  medi_posologia VARCHAR(200) NULL,
  medi_dosagem VARCHAR(50) NULL,
  medi_via VARCHAR(50) NULL,
  medi_quanti_estoque INTEGER UNSIGNED NULL,
  PRIMARY KEY(medi_id),
  FOREIGN KEY(paci_id)
    REFERENCES paciente(paci_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE dieta (
  diet_id BIGINT NOT NULL AUTO_INCREMENT,
  paci_id BIGINT NULL,
  diet_nome VARCHAR(150) NULL,
  diet_porcao VARCHAR(50) NULL,
  diet_descricao VARCHAR(200) NULL,
  PRIMARY KEY(diet_id),
  FOREIGN KEY(paci_id)
    REFERENCES paciente(paci_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE evento_atendimento (
  event_id BIGINT NOT NULL AUTO_INCREMENT,
  medi_id BIGINT NULL,
  diet_id BIGINT NULL,
  paci_id BIGINT NULL,
  cuid_id BIGINT NULL,
  event_dt_inicio DATETIME NOT NULL,
  event_dt_fim DATETIME NOT NULL,
  event_hr_adm DATETIME NULL,
  event_titulo VARCHAR(50) NULL,
  event_descricao VARCHAR(200) NULL,
  event_status BOOL NULL,
  PRIMARY KEY(event_id),
  FOREIGN KEY(cuid_id)
    REFERENCES cuidador(cuid_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(paci_id)
    REFERENCES paciente(paci_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(diet_id)
    REFERENCES dieta(diet_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(medi_id)
    REFERENCES medicamento(medi_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


