CREATE TABLE atendimento (
  aten_id BIGINT NOT NULL AUTO_INCREMENT,
  paciente_paci_id BIGINT NOT NULL,
  cuidador_cuid_id BIGINT NOT NULL,
  aten_dt_inicial DATE NULL,
  aten_dt_final DATE NULL,
  aten_hr_inicial DATETIME NULL,
  aten_hr_final DATETIME NULL,
  aten_local VARCHAR NULL,
  PRIMARY KEY(aten_id),
  INDEX Atendimento_FKIndex2(paciente_paci_id),
  INDEX Atendimento_FKIndex2(cuidador_cuid_id)
);

CREATE TABLE cuidador (
  cuid_id BIGINT NOT NULL AUTO_INCREMENT,
  cuid_nome VARCHAR NULL,
  cuid_fone VARCHAR NULL,
  cuid_email VARCHAR NULL,
  cuid_dt_nascimento DATE NULL,
  cuid_formacao VARCHAR NULL,
  cuid_info_adicional VARCHAR NULL,
  cuid_localizacao VARCHAR NULL,
  cuid_estatus INTEGER UNSIGNED NULL,
  PRIMARY KEY(cuid_id)
);

CREATE TABLE evento_medicacao (
  event_id BIGINT NOT NULL AUTO_INCREMENT,
  atendimento_aten_id BIGINT NOT NULL,
  event_data_hora DATETIME NULL,
  event_titulo VARCHAR NULL,
  event_informacao VARCHAR NULL,
  event_estatus INTEGER UNSIGNED NULL,
  PRIMARY KEY(event_id),
  INDEX Evento_Medicacao_FKIndex1(atendimento_aten_id)
);

CREATE TABLE medicamento (
  medi_id BIGINT NOT NULL AUTO_INCREMENT,
  medi_nome VARCHAR NULL,
  medi_concentracao DOUBLE NULL,
  medi_material VARCHAR NULL,
  medi_via_adm INTEGER UNSIGNED NULL,
  medi_tipo INTEGER UNSIGNED NULL,
  medi_dt_validade DATE NULL,
  medi_estoque DOUBLE NULL,
  PRIMARY KEY(medi_id)
);

CREATE TABLE Medicamento_has_Evento_Medicacao (
  medicamento_medi_id BIGINT NOT NULL,
  evento_medicacao_event_id BIGINT NOT NULL,
  PRIMARY KEY(medicamento_medi_id, evento_medicacao_event_id),
  INDEX Medicamento_has_Evento_Medicacao_FKIndex1(medicamento_medi_id),
  INDEX Medicamento_has_Evento_Medicacao_FKIndex2(evento_medicacao_event_id)
);

CREATE TABLE paciente (
  paci_id BIGINT NOT NULL AUTO_INCREMENT,
  paci_idnome VARCHAR NULL,
  paci_fone VARCHAR NULL,
  paci_dt_nascimento DATE NULL,
  paci_endereco VARCHAR NULL,
  paci_no_familiar VARCHAR NULL,
  paci_fone_familiar VARCHAR NULL,
  paci_email_familiar VARCHAR NULL,
  PRIMARY KEY(paci_id)
);


