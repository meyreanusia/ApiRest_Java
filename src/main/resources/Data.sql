CREATE TABLE tbUnidade (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_Unidade PRIMARY KEY (ID)
);
CREATE TABLE tbTipoLancamento (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_TipoLancamento PRIMARY KEY (ID)
);
CREATE TABLE tbSolicitante (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_Solicitante PRIMARY KEY (ID)
);
CREATE TABLE tbObjetivoEstrategico (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_ObjetivoEstrategico PRIMARY KEY (ID)
);
CREATE TABLE tbUnidadeOrcamentaria (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Codigo int NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_UnidadeOrcamentaria PRIMARY KEY (ID),
  CONSTRAINT UQ_UnidadeOrcamentaria UNIQUE (Codigo)
);
CREATE TABLE tbPrograma (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Codigo int NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_Programa PRIMARY KEY (ID),
  CONSTRAINT UQ_Programa UNIQUE (Codigo)
);
CREATE TABLE tbFonteRecurso (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Codigo int NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_FonteRecurso PRIMARY KEY (ID),
  CONSTRAINT UQ_FonteRecurso UNIQUE (Codigo)
);
CREATE TABLE tbAcao (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Codigo int NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_Acao PRIMARY KEY (ID),
  CONSTRAINT UQ_Acao UNIQUE (Codigo)
);
CREATE TABLE tbGrupoDespesa (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Codigo float NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_GrupoDespesa PRIMARY KEY (ID),
  CONSTRAINT UQ_GrupoDespesa UNIQUE (Codigo)
);
CREATE TABLE tbModalidadeAplicacao (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Codigo int NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_ModalidadeAplicacao PRIMARY KEY (ID),
  CONSTRAINT UQ_ModalidadeAplicacao UNIQUE (Codigo)
);
CREATE TABLE tbElementoDespesa (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Codigo int NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_ElementoDespesa PRIMARY KEY (ID),
  CONSTRAINT UQ_ElementoDespesa UNIQUE (Codigo)
);
CREATE TABLE tbTipoTransacao (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Nome varchar(255) NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  CONSTRAINT PK_TipoTransacao PRIMARY KEY (ID)
);
CREATE TABLE tbLancamentos (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  LancamentoInvalido bit NOT NULL,
  NumeroLancamento int NULL,
  IDTipoLancamento int NOT NULL,
  DataLancamento date NOT NULL,
  IDLancamentoPai int NULL,
  IDUnidade int NOT NULL,
  Descricao varchar(255) NOT NULL,
  IDUnidadeOrcamentaria int NOT NULL,
  IDPrograma int NOT NULL,
  IDAcao int NOT NULL,
  IDFonteRecurso int NOT NULL,
  IDGrupoDespesa int NOT NULL,
  IDModalidadeAplicacao int NOT NULL,
  IDElementoDespesa int NOT NULL,
  IDSolicitante int NULL,
  GED varchar(27) NULL,
  Contratado varchar(255) NULL,
  IDObjetivoEstrategico int NULL,
  Valor real NOT NULL,
  IDTipoTransacao int NOT NULL,
  DataCadastro datetime NOT NULL,
  DataAlteracao datetime NULL,
  AnoOrcamento smallint NOT NULL,
  CONSTRAINT PK_Lancamentos PRIMARY KEY (ID),
  CONSTRAINT fk_Lancamentos_TipoLancamento FOREIGN KEY (IDTipoLancamento) REFERENCES tbTipoLancamento (ID),
  CONSTRAINT fk_Lancamentos_Unidade FOREIGN KEY (IDUnidade) REFERENCES tbUnidade (ID),
  CONSTRAINT fk_Lancamentos_UnidadeOrcamentaria FOREIGN KEY (IDUnidadeOrcamentaria) REFERENCES tbUnidadeOrcamentaria (ID),
  CONSTRAINT fk_Lancamentos_ElementoDespesa FOREIGN KEY (IDElementoDespesa) REFERENCES tbElementoDespesa (ID),
  CONSTRAINT fk_Lancamentos_Acao FOREIGN KEY (IDAcao) REFERENCES tbAcao (ID),
  CONSTRAINT fk_Lancamentos_Programa FOREIGN KEY (IDPrograma) REFERENCES tbPrograma (ID),
  CONSTRAINT fk_Lancamentos_Solicitante FOREIGN KEY (IDSolicitante) REFERENCES tbSolicitante (ID),
  CONSTRAINT fk_Lancamentos_ObjetivoEstrategico FOREIGN KEY (IDObjetivoEstrategico) REFERENCES tbObjetivoEstrategico (ID),
  CONSTRAINT fk_Lancamentos_GrupoDespesa FOREIGN KEY (IDGrupoDespesa) REFERENCES tbGrupoDespesa (ID),
  CONSTRAINT fk_Lancamentos_ModalidadeAplicacao FOREIGN KEY (IDModalidadeAplicacao) REFERENCES tbModalidadeAplicacao (ID),
  CONSTRAINT fk_Lancamentos_TipoTransacao FOREIGN KEY (IDTipoTransacao) REFERENCES tbTipoTransacao (ID),
  CONSTRAINT fk_Lancamentos_FonteRecurso FOREIGN KEY (IDFonteRecurso) REFERENCES tbFonteRecurso (ID),
  CONSTRAINT fk_Lancamentos_Lancamentos FOREIGN KEY (IDLancamentoPai) REFERENCES tbLancamentos (ID)
);

INSERT INTO tbPrograma(codigo,nome,dataCadastro) VALUES (4,'Defesa da Ordem Jurídica e Social',CURRENT_TIMESTAMP());
INSERT INTO tbPrograma(codigo,nome,dataCadastro) VALUES (31,'Gestão e Manutenção do Ministério Público',CURRENT_TIMESTAMP());

INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Proposta Inicial da Unidade',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Aprovação do Colégio de Procuradores de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Aprovação do Governo (Cota Orçamentária)',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Revisão da Proposta Inicial da Unidade',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Alteração da Revisão da Proposta Inicial da Unidade',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Remanejamento',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Crédito Suplementar Interno',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Crédito Suplementar Entrada',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Crédito Suplementar Saída',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Crédito Extraordinário Interno',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Crédito Extraordinário Entrada',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Crédito Extraordinário Saída',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Crédito Especial Interno',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Crédito Especial Entrada',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Crédito Especial Saída',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Transferência',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Reserva de Dotação',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Alteração de Reserva de Dotação',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Apostilamento',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Alteração de Apostilamento',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Empenho COM Reserva de Dotação',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Empenho SEM Reserva de Dotação',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Alteração de Empenho',CURRENT_TIMESTAMP());
INSERT INTO tbTipoLancamento(nome,dataCadastro) VALUES ('Despesa Não Prevista',CURRENT_TIMESTAMP());

INSERT INTO tbUnidade(nome,dataCadastro)VALUES('Diretoria Administrativa',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro)VALUES('Diretoria de Recursos Humanos',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Diretoria de Tecnologia da Informação e Comunicação',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Diretoria de Gestão Estratégica e Orçamentária',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Diretoria Financeira',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Divisão de Auditoria Interna',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Escola Superior do Ministério Público',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Centro de Apoio Operacional Direitos Humanos',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Centro de Apoio Operacional Educação',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Centro de Apoio Operacional Infância e Adolescência',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Centro de Apoio Operacional Meio Ambiente',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Centro de Apoio Operacional Mulher',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Centro de Apoio Operacional Patrimônio Público',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Centro de Apoio Operacional Saúde',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Centro de Apoio Operacional São Francisco',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Centro de Apoio Operacional Segurança Pública',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Centro de Apoio Operacional Atividade Criminal',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Centro Médico',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Coordenadoria Permanente de Autocomposição e Paz',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Coordenadoria de Apoio aos Promotores Eleitorais',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Coordenadoria de Documentação e Memória',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Coordenadoria-Geral',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Corregedoria-Geral',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Divisão de Comunicação, Cerimonial e Eventos',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Divisão de Perícia Contábil',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Divisão de Perícia Técnica',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Divisão de Equipe Interdisciplinar',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Gabinete Procuradoria-Geral',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Grupo de Atuação Especial de Combate ao Crime Organizado',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Gabinete de Segurança Institucional',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Ouvidoria',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Promotoria de Controle Externo',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Promotoria de Defesa da Saúde',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Promotoria de Direitos do Cidadão',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Secretaria-Geral',CURRENT_TIMESTAMP());
INSERT INTO tbUnidade(nome,dataCadastro) VALUES ('Subprocuradoria-Geral',CURRENT_TIMESTAMP());

INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Diretoria Administrativa',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Diretoria de Recursos Humanos',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Diretoria de Tecnologia da Informação e Comunicação',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Diretoria de Gestão Estratégica e Orçamentária',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Divisão de Auditoria Interna',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Escola Superior do Ministério Público',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Procuradoria-Geral de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Subprocuradoria-Geral de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Coordenadoria Recursal',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Gabinete de Segurança Institucional',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Comissão de Concurso',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Gabinete do Grupo de Atuação Especial de Combate ao Crime Organizado',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Coordenadoria Permanente de Autocomposição e Paz',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Coordenadoria de Promoção de Igualdade Étnico-Racial',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Cartório 2º Grau',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Divisão de Comunicação, Cerimonial e Eventos',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Secretaria-Geral',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Grupo de Apoio Operacional da Secretaria Geral',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Assessoria Jurídica',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Centro Médico',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Central de Expedições de Diligências',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Divisão de Material',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Divisão de Apoio Administrativo',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Divisão de Engenharia e Manutenção',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Divisão de Patrimônio',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Diretoria Financeira',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Corregedoria-Geral',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Coordenadoria-Geral',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Ouvidoria',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Grupo de Combate a Improbidade Administrativa',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Colégio de Procuradores de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Conselho Superior do Ministério Público',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Centro de Apoio Operacional de Segurança Pública',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Centro de Apoio Operacional da Infância e da Adolescência',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Centro de Apoio Operacional de Defesa do Patrimônio Público, da Ordem Tributária e do Terceiro Setor',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Centro de Apoio Operacional Atividades Cíveis e Criminais',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Divisão de Perícia Técnica',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Divisão de Perícia Contábil',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Divisão de Equipe Interdisciplinar',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Centro de Apoio Operacional de Defesa dos Direitos Humanos',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Centro de Apoio Operacional de Proteção ao Rio São Francisco e às Nascentes',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Centro de Apoio Operacional dos Direitos à Educação',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Centro de Apoio Operacional dos Direitos à Saúde',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Centro de Apoio Operacional do Meio Ambiente, Urbanismo, Patrimônio Social e Cultural',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Centro de Apoio Operacional dos Direitos da Mulher',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Coordenadoria de Apoio aos Promotores Eleitorais',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('3ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('4ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('5ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('6ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('7ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('8ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('9ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('10ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('11ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('12ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('13ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('14ª Procuradoria de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria Direitos do Cidadão - Defesa do Patrimônio Público e na área de Previdência Pública',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria Direitos do Cidadão - Defesa dos Direitos à Saúde',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('3ª Promotoria Direitos do Cidadão - Controle Externo da Atividade policial e em Questões Agrárias',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('4ª Promotoria Direitos do Cidadão - Defesa do Acidentado do Trabalho, do Idoso, do Deficiente, dos Direitos Humanos em Geral e dos Direitos à Assistência Social',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('5ª Promotoria Direitos do Cidadão - Controle e Fiscalização do Terceiro Setor ',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('6º Promotoria Direitos do Cidadão - Defesa do Direito à Educação',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('7ª Promotoria Direitos do Cidadão - Defesa da Ordem Tributária',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('8ª Promotoria Direitos do Cidadão - Defesa dos Direitos da Criança e do Adolescente',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('9ª Promotoria Direitos do Cidadão - Defesa dos Direitos à Saúde',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('10ª Promotoria Direitos do Cidadão - Defesa do Meio Ambiente, Urbanismo, Patrimônio Social e Cultural e dos Serviços de Relevância Pública',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria (1ª, 2º, 4º, 6º, 7º, 8º, 9º Varas Cíveis) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria (14ª Vara Cível) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('3ª Promotoria Cível de Aracaju (5ª, 10ª, 11ª, 13ª, 15ª e 21ª Varas Cíveis) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria da Curadoria da Fazenda Pública (3ª Vara Cível) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria da Curadoria da Fazenda Pública (12ª Vara Cível) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('3ª Promotoria da Curadoria da Fazenda Pública (18ª Vara Cível) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('4ª Promotoria da Curadoria da Fazenda Pública (Juizado Especial da Fazenda Pública) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria Criminal (1ª Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria Criminal (2º Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('3ª Promotoria Criminal (3ª Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('4ª Promotoria Criminal (4ª Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Defesa do Consumidor de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('5ª Promotoria Criminal (9ª Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('6ª Promotoria Criminal (11ª Vara Criminal - Juizado de Violência Doméstica contra a Mulher) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria Do Tribunal do Júri (5ª Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria Do Tribunal do Júri  (8ª Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('3ª  Promotoria Do Tribunal do Júri (5ª Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('4ª  Promotoria Do Tribunal do Júri (8ª Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria Militar (Auditoria Militar - 6º Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria das Execuções Criminais (7ª Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('3ª Promotoria das Execuções Criminais (7ª Vara Criminal) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Acidentes e de Delitos de Trânsito de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria da Curadoria da Infância e da Adolescência (Juizado da Infância e Juventude - 16ª Vara Cível) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria da Curadoria da Infância e da Adolescência (Juizado da Infância e Juventude - 17ª Vara Cível) de Aracaju',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('5ª Promotoria Distrital - (5ª Vara de Assistência Judiciária - 26ª Vara Cível)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('6ª Promotoria Distrital (6ª Vara de Assistência Judiciária - 27ª Vara Cível)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria Distrital (1ª Vara de Assistência Judiciária - 19ª Vara Cível)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('3ª Promotoria Distrital (3ª Vara de Assistência Judiciária - 24ª Vara Cível)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria Distrital (2ª Vara de Assistência Judiciária - 23ª Vara Cível)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('4ª Promotoria Distrital (4ª Vara de Assistência Judiciária - 25ª Vara Cível)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria Especial Cível e Criminal  de Aracaju (1º JESP Criminal)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('7ª Promotoria Distrital (7ª Vara de Assistência Judiciária - 28ª Vara Cível)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça Criminal de Estância (Vara Criminal)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça de Estância (1ª Vara Cível)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça de Estância (1ª Vara Cível)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça Criminal de Estância',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça Especial Cível e Criminal (JESP Cível e Criminal) de Estância',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça Criminal de Itabaiana (1ª Vara Criminal)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça Criminal de Itabaiana (2º Vara Criminal)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça (1ª Vara Cível) de Itabaiana',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça (2ª Vara Cível) de Itabaiana',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça Especial Cível e Criminal (JESP Cível e Criminal) de Itabaiana',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria das Execuções Criminais (10ª Vara Criminal - Vara de Execuções das Medidas e Penas Alternativas)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça de Canindé do São Francisco ',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Coordenadoria de Documentação e Memória',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça de  Itaporanga D Ajuda',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça de  Itaporanga D Ajuda',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça de Laranjeiras',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça de Laranjeiras',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça Criminal (Vara Criminal) de Lagarto',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça Criminal (Vara Criminal) de Lagarto',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça (Vara Cível) de Lagarto',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça (Vara Cível) de Lagarto',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça Especial Cível e Criminal (JESP Cível e Criminal) de Lagarto',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça de Nossa Senhora da Glória',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça de Nossa Senhora da Glória',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça (Vara Cível) de Nossa Senhora do Socorro (SEDE)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça Criminal  (1ª Vara Criminal) de Nossa Senhora do Socorro (SEDE)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça Criminal  (2ª Vara Criminal) de Nossa Senhora do Socorro (SEDE)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('3ª Promotoria de Justiça Criminal (3ª Vara Criminal) de Nossa Senhora do Socorro (SEDE)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça Especial de Nossa Senhora do Socorro (2º Juizado Especial Cível e Criminal) (SEDE)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça Especial (1º JESP Cível e Criminal) de Nossa Senhora do Socorro (SEDE)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça Distrital (Vara de Assistência Judiciária) (3ª Vara Cível) de N. S. do Socorro (MARCOS FREIRE)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça Distrital (4ª Vara Cível) de Nossa Senhora do Socorro',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça de Propriá',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça de Propriá',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça de São Cristóvão (1ª Vara Cível)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça Criminal (Vara Criminal) de São Cristóvão (SEDE)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça Criminal (Vara Criminal) de São Cristóvão',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça Distrital de São Cristóvão (2ª Vara Cível) (CAMPUS DA UFS)',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça Especial Cível e Criminal de São Cristóvão',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça de Simão Dias',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça de Simão Dias',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça de Tobias Barreto',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça de Tobias Barreto',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça de Aquidabã',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça de Arauá',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça de Riachão do Dantas',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça da Barra dos Coqueiros',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça da Barra dos Coqueiros',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1ª Promotoria de Justiça de Boquim',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça de Campo do Brito ',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça de Capela',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça de Carira',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça de Carmópolis ',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça de Cedro de São João',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Cristinápolis',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Frei Paulo',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Gararu',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Indiaroba',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Itabaianinha',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Japaratuba',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Malhador',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Maruim',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1º Promotoria de Neópolis',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça de Neópolis',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('1º Promotoria de Nossa Senhora das Dores',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('2ª Promotoria de Justiça de Nossa Senhora das Dores',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Justiça de Pacatuba',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Poço Redondo',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Poço Verde  ',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Porto da Folha ',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Riachuelo',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Ribeirópolis',CURRENT_TIMESTAMP());
INSERT INTO tbSolicitante(nome,dataCadastro) VALUES ('Promotoria de Umbaúba',CURRENT_TIMESTAMP());

INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Incrementar o diálogo e a atuação conjunta do MP com os órgãos públicos e instituições não governamentais de defesa do consumidor',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Fortalecer as redes de atendimento a grupos vulneráveis junto aos Órgãos Públicos',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Atuar na universalização do acesso à educação e à saúde com a prestação de serviços de qualidade',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Aprimorar as atividades de combate à corrupção, defesa do patrimônio público e fiscalização do terceiro setor',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Contribuir para a preservação do meio ambiente e patrimônio histórico e cultural e para o desenvolvimento urbano de forma sustentável, em sintonia com as demais instituições e com a sociedade em geral',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Intensificar o combate à criminalidade e o efetivo controle externo da atividade policial',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Aprimorar, informatizar e desburocratizar as rotinas administrativas',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Potencializar práticas resolutivas da atuação ministerial',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Aprimorar o processo de gestão e governança',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Capacitar, valorizar e motivar todos que atuam na instituição',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Promover a qualidade de vida no trabalho e a valorização dos Membros e Servidores',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Aperfeiçoar a comunicação efetiva com a sociedade e o relacionamento institucional',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Modernizar e adequar a infraestrutura física',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Prover soluções de TI, entregando benefícios, mitigando riscos e otimizando recursos',CURRENT_TIMESTAMP());
INSERT INTO tbObjetivoEstrategico(nome,dataCadastro) VALUES ('Assegurar recursos orçamentários, otimizar sua alocação e aperfeiçoar o gerenciamento',CURRENT_TIMESTAMP());

INSERT INTO tbUnidadeOrcamentaria(codigo,nome,dataCadastro) VALUES (11101,'Procuradoria-Geral de Justiça',CURRENT_TIMESTAMP());
INSERT INTO tbUnidadeOrcamentaria(codigo,nome,dataCadastro) VALUES (11402,'Fundo para Reconstituição de Bens Lesados',CURRENT_TIMESTAMP());
INSERT INTO tbUnidadeOrcamentaria(codigo,nome,dataCadastro) VALUES (11401,'Fundo Especial do Ministério Público',CURRENT_TIMESTAMP());

INSERT INTO tbFonteRecurso(codigo,nome,dataCadastro) VALUES (1500,'Recursos do Tesouro do Estado',CURRENT_TIMESTAMP());
INSERT INTO tbFonteRecurso(codigo,nome,dataCadastro) VALUES (1501,'Outros Recursos não Vinculados',CURRENT_TIMESTAMP());
INSERT INTO tbFonteRecurso(codigo,nome,dataCadastro) VALUES (1700,'Recursos de Convênio - Governo Federal',CURRENT_TIMESTAMP());
INSERT INTO tbFonteRecurso(codigo,nome,dataCadastro) VALUES (1703,'Recursos de Convênio - Privado',CURRENT_TIMESTAMP());
INSERT INTO tbFonteRecurso(codigo,nome,dataCadastro) VALUES (1755,'Receita de Alienação de Bens',CURRENT_TIMESTAMP());
INSERT INTO tbFonteRecurso(codigo,nome,dataCadastro) VALUES (1759,'Recursos Próprios (Fundos)',CURRENT_TIMESTAMP());

INSERT INTO tbGrupoDespesa(codigo,nome,dataCadastro) VALUES (3.1,'Pessoal',CURRENT_TIMESTAMP());
INSERT INTO tbGrupoDespesa(codigo,nome,dataCadastro) VALUES (3.3,'Outras Despesas Correntes',CURRENT_TIMESTAMP());
INSERT INTO tbGrupoDespesa(codigo,nome,dataCadastro) VALUES (4.4,'Investimentos',CURRENT_TIMESTAMP());

INSERT INTO tbModalidadeAplicacao(codigo,nome,dataCadastro) VALUES (30,'Transferências a Estados e ao Distrito Federal',CURRENT_TIMESTAMP());
INSERT INTO tbModalidadeAplicacao(codigo,nome,dataCadastro) VALUES (40,'Transferências a Municípios',CURRENT_TIMESTAMP());
INSERT INTO tbModalidadeAplicacao(codigo,nome,dataCadastro) VALUES (50,'Transferências a Instituições Privadas sem Fins Lucrativos',CURRENT_TIMESTAMP());
INSERT INTO tbModalidadeAplicacao(codigo,nome,dataCadastro) VALUES (90,'Aplicação Direta',CURRENT_TIMESTAMP());
INSERT INTO tbModalidadeAplicacao(codigo,nome,dataCadastro) VALUES (91,'Aplicação Indireta',CURRENT_TIMESTAMP());

INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (7,'Contribuição a Entidades Fechadas de Previdência',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (8,'Outros Benefícios Assistenciais',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (9,'Salário Família',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (11,'Vencimentos e Vantagens Fixas – Pessoal Civil',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (13,'Obrigações Patronais',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (14,'Diárias-civil ',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (16,'Outras Despesa Variáveis – Pessoal Civil',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (30,'Material de Consumo',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (32,'Material de Distribuição Gratuita',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (33,'Passagens e Despesas com Locomoção ',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (35,'Serviços de Consultoria',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (36,'Outros Serviços de Terceiros Pessoa Física',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (37,'Locação de Mão de Obra',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (39,'Outros Serviços de Terceiros Pessoa Jurídica',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (40,'Serviços de Tecnologia da Informação e Comunicação - Pessoa Jurídica',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (41,'Contribuições',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (46,'Auxílio-Alimentação',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (47,'Obrigações Tributárias e Contributivas',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (48,'Outros Auxílios Financeiros a Pessoa Física',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (51,'Obras e Instalações',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (52,'Equipamentos e Material Permanente',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (92,'Despesas de Exercícios Anteriores',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (93,'Outras Restituições e Indenizações',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (94,'Indenizações e Restituições Trabalhistas ',CURRENT_TIMESTAMP());
INSERT INTO tbElementoDespesa(codigo,nome,dataCadastro) VALUES (96,'Ressarcimento de Despesas de Pessoal Requisitado',CURRENT_TIMESTAMP());

INSERT INTO tbAcao(codigo,nome,dataCadastro) VALUES (2905,'Teste tabela de Acao',CURRENT_TIMESTAMP());

INSERT INTO tbTipoTransacao(nome,dataCadastro) VALUES ('Teste tabela de Tipo Transacao',CURRENT_TIMESTAMP());

CREATE VIEW IF NOT EXISTS VW_LANCAMENTOS AS
SELECT
    L.id,
    L.lancamentoInvalido,
    L.numeroLancamento,
    L.descricao,
    L.dataLancamento,
    L.idLancamentoPai,
    L.valor,
    TL.nome as dsTipoLancamento,
    U.nome AS dsUnidade,
    UO.nome AS dsUnidadeOrcamentaria,
    P.nome AS dsPrograma,
    A.nome AS dsAcao,
    FR.nome AS dsFonteRecurso,
    GD.nome AS dsGrupoDespesa,
    MA.nome AS dsModalidadeAplicacao,
    ED.nome AS dsElementoDespesa,
    CASE WHEN S.nome IS NULL THEN 'Não Informado' ELSE S.nome END AS dsSolicitante,
    CASE WHEN OE.nome IS NULL THEN 'Não Informado' ELSE OE.nome END AS dsObjetivoEstrategico,
    TT.nome AS dsTipoTransacao,
    L.GED,
    L.contratado,
    L.anoOrcamento
FROM TBLANCAMENTOS L
JOIN TBTIPOLANCAMENTO TL ON L.idTipoLancamento = TL.id
JOIN TBUNIDADE U ON L.idUnidade = U.id
JOIN TBUNIDADEORCAMENTARIA UO ON L.idUnidadeOrcamentaria = UO.id
JOIN TBELEMENTODESPESA ED ON L.idElementoDespesa = ED.id
JOIN TBACAO A ON L.idAcao = A.id
JOIN TBPROGRAMA P ON L.idPrograma = P.id
LEFT JOIN TBSOLICITANTE S ON L.idSolicitante = S.id
LEFT JOIN TBOBJETIVOESTRATEGICO OE ON L.idObjetivoEstrategico = OE.id
JOIN TBGRUPODESPESA GD ON L.idGrupoDespesa = GD.id
JOIN TBMODALIDADEAPLICACAO MA ON L.idModalidadeAplicacao = MA.id
JOIN TBTIPOTRANSACAO TT ON L.idTipoTransacao = TT.id
JOIN TBFONTERECURSO FR ON L.idFonteRecurso = FR.id;



