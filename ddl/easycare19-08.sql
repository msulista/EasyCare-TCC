CREATE TABLE dieta (
  diet_id BIGINT NOT NULL AUTO_INCREMENT,
  diet_nome VARCHAR(150) NULL,
  diet_porcao VARCHAR(50) NULL,
  diet_descricao VARCHAR(200) NULL,
  diet_valorNutri NUMERIC NULL,
  diet_tp_refeicao INTEGER UNSIGNED NULL,
  PRIMARY KEY(diet_id)
);

CREATE TABLE medicamento (
  medi_id BIGINT NOT NULL AUTO_INCREMENT,
  medi_nome VARCHAR(150) NULL,
  medi_concentracao VARCHAR(200) NULL,
  medi_material VARCHAR(50) NULL,
  medi_via VARCHAR(50) NULL,
  medi_quanti_estoque INTEGER UNSIGNED NULL,
  medi_dt_validade DATE NULL,
  medi_tipo INTEGER UNSIGNED NULL,
  PRIMARY KEY(medi_id)
);

CREATE TABLE paciente (
  paci_id BIGINT NOT NULL AUTO_INCREMENT,
  paci_nome VARCHAR(150) NOT NULL,
  paci_dt_nascimento DATE NOT NULL,
  paci_fone VARCHAR(10) NOT NULL,
  paci_no_familiar VARCHAR(150) NULL,
  paci_fone_familiar VARCHAR(10) NULL,
  paci_endereco VARCHAR(250) NOT NULL,
  paci_email_familiar VARCHAR(100) NULL,
  PRIMARY KEY(paci_id)
);

CREATE TABLE cuidador (
  cuid_id BIGINT NOT NULL AUTO_INCREMENT,
  cuid_nome VARCHAR(150) NOT NULL,
  cuid_fone VARCHAR(10) NULL,
  cuid_dt_nascimento DATE NULL,
  cuid_email VARCHAR(100) NOT NULL,
  cuid_formacao VARCHAR(100) NULL,
  cuid_descricao VARCHAR(250) NULL,
  cuid_localizacao VARCHAR(50) NULL,
  cuid_status INTEGER UNSIGNED NULL,
  PRIMARY KEY(cuid_id)
);

CREATE TABLE atendimento (
  atend_id BIGINT NOT NULL AUTO_INCREMENT,
  cuid_id BIGINT NOT NULL,
  paci_id BIGINT NOT NULL,
  atend_dt_inicial DATE NULL,
  atend_dt_final DATE NULL,
  atend_hr_inicial DATETIME NULL,
  atend_hr_final DATETIME NULL,
  atend_local VARCHAR(50) NULL,
  PRIMARY KEY(atend_id),
  FOREIGN KEY(paci_id)
    REFERENCES paciente(paci_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(cuid_id)
    REFERENCES cuidador(cuid_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE evento_medicacao (
  event_id BIGINT NOT NULL AUTO_INCREMENT,
  atend_id BIGINT NOT NULL,
  event_dt_hr DATETIME NOT NULL,
  event_titulo VARCHAR(50) NULL,
  event_descricao VARCHAR(200) NULL,
  event_status INTEGER UNSIGNED NULL,
  PRIMARY KEY(event_id),
  FOREIGN KEY(atend_id)
    REFERENCES atendimento(atend_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE medicamento_has_evento_medicacao (
  medi_id BIGINT NOT NULL,
  event_id BIGINT NOT NULL,
  PRIMARY KEY(medi_id, event_id),
  FOREIGN KEY(medi_id)
    REFERENCES medicamento(medi_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(event_id)
    REFERENCES evento_medicacao(event_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE dieta_has_evento_medicacao (
  diet_id BIGINT NOT NULL,
  event_id BIGINT NOT NULL,
  PRIMARY KEY(diet_id, event_id),
  FOREIGN KEY(diet_id)
    REFERENCES dieta(diet_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(event_id)
    REFERENCES evento_medicacao(event_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


