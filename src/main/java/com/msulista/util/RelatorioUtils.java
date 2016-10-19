package com.msulista.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.msulista.enums.RelatorioEnum;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 * Classe utilit�ria para auxiliar na gera��o de relat�rios utilizando o Jasper
 * Reports.
 */
public class RelatorioUtils {

	@Inject
	private Logger logger;

	/**
	 * Gera o relat�rio PDF e retorna o array de bytes do mesmo.
	 *
	 * @param relatorioEnum
	 *            {@link RelatorioEnum} indica o relat�rio que deve ser gerado
	 * @param parametros
	 *            Mapa que cont�m os par�metros a serem passados para o Jasper
	 *            Reports na gera��o do relat�rio.
	 * @return Array de bytes do relat�rio gerado.
	 */
	public byte[] gerarRelatorioPdf(final RelatorioEnum relatorioEnum, final Map<String, Object> parametros) {
		return this.geraRelatorio(relatorioEnum, null, parametros, true);
	}

	/**
	 * Gera o relat�rio Excel e retorna o array de bytes do mesmo.
	 *
	 * @param relatorioEnum
	 *            {@link RelatorioEnum} indica o relat�rio que deve ser gerado
	 * @param parametros
	 *            Mapa que cont�m os par�metros a serem passados para o Jasper
	 *            Reports na gera��o do relat�rio.
	 * @return Array de bytes do relat�rio gerado.
	 */
	public byte[] gerarRelatorioExcel(final RelatorioEnum relatorioEnum, final Map<String, Object> parametros) {
		return this.geraRelatorio(relatorioEnum, null, parametros, false);
	}

	/**
	 * Gera o relat�rio PDF e retorna o array de bytes do mesmo.
	 *
	 * @param relatorioEnum
	 *            {@link RelatorioEnum} indica o relat�rio que deve ser gerado
	 * @param beanCollection
	 *            {@link Collection} cole��o de objetos a serem passados por
	 *            par�metro para gera��o do relat�rio.
	 * @param parametros
	 *            Mapa que cont�m os par�metros a serem passados para o Jasper
	 *            Reports na gera��o do relat�rio.
	 * @return Array de bytes do relat�rio gerado.
	 */
	@SuppressWarnings("rawtypes")
	public byte[] gerarRelatorioPdf(final RelatorioEnum relatorioEnum, final Collection beanCollection,
			final Map<String, Object> parametros) {
		return this.geraRelatorio(relatorioEnum, beanCollection, parametros, true);
	}

	/**
	 * Gera o relat�rio Excel e retorna o array de bytes do mesmo.
	 *
	 * @param relatorioEnum
	 *            {@link RelatorioEnum} indica o relat�rio que deve ser gerado
	 * @param beanCollection
	 *            {@link Collection} cole��o de objetos a serem passados por
	 *            par�metro para gera��o do relat�rio.
	 * @param parametros
	 *            Mapa que cont�m os par�metros a serem passados para o Jasper
	 *            Reports na gera��o do relat�rio.
	 * @return Array de bytes do relat�rio gerado.
	 */
	@SuppressWarnings("rawtypes")
	public byte[] gerarRelatorioExcel(final RelatorioEnum relatorioEnum, final Collection beanCollection,
			final Map<String, Object> parametros) {
		return this.geraRelatorio(relatorioEnum, beanCollection, parametros, false);
	}

	/**
	 * Gera o relat�rio em PDF ou Excel e retorna o array de bytes do mesmo.
	 *
	 * @param relatorioEnum
	 *            {@link RelatorioEnum} indica o relat�rio que deve ser gerado
	 * @param beanCollection
	 *            {@link Collection} cole��o de objetos a serem passados por
	 *            par�metro para gera��o do relat�rio.
	 * @param parametros
	 *            Mapa que cont�m os par�metros a serem passados para o Jasper
	 *            Reports na gera��o do relat�rio.
	 * @param isPDF
	 *            true quando o relat�rio ser� gerado em PDF. false quando ser�
	 *            gerado em Excel.
	 * @return Array de bytes do relat�rio gerado.
	 */
	@SuppressWarnings("rawtypes")
	private byte[] geraRelatorio(final RelatorioEnum relatorioEnum, final Collection beanCollection,
			final Map<String, Object> parametros, final boolean isPDF) {
		if (relatorioEnum == null) {
			Mensagem.add("Falha ao gerar o relat�rio.");
		}

		try (OutputStream outputStream = new ByteArrayOutputStream()) {
			final Map<String, Object> parametrosJasper = new HashMap<>();
			if (parametros != null && !parametros.isEmpty()) {
				parametrosJasper.putAll(parametros);
			}

			final InputStream inputStream = this.getClass().getResourceAsStream(relatorioEnum.getCaminho());

			final JRDataSource dataSource = beanCollection != null ? new JRBeanCollectionDataSource(beanCollection)
					: new JREmptyDataSource();

			final JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametrosJasper, dataSource);

			final List<JasperPrint> jasperPrintList = new ArrayList<>();
			jasperPrintList.add(jasperPrint);

			final Exporter exporter = this.criarExporter(jasperPrintList, outputStream, isPDF);
			exporter.exportReport();

			return ((ByteArrayOutputStream) outputStream).toByteArray();
		} catch (IOException | JRException exception) {
			exception.printStackTrace();
			this.logger.log(Level.SEVERE, "Erro ao gerar o relat�rio.", exception);
			Mensagem.add("Erro ao gerar o relat�rio.");
		}
		return new byte[1];
	}

	/**
	 * Cria o Exporter de PDF ou Excel, de acordo com os par�metros informados.
	 *
	 * @param jasperPrintList
	 *            lista que cont�m os {@link JasperPrint} a serem utilizados na
	 *            exporta��o.
	 * @param outputStream
	 *            {@link OutputStream} que ir� cont�r o array de bytes do
	 *            arquivo exportado.
	 * @param isPDF
	 *            true caso seja PDF, false caso seja Excel.
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Exporter criarExporter(final List<JasperPrint> jasperPrintList, final OutputStream outputStream,
			final boolean isPDF) {
		final ExporterInput exporterInput = SimpleExporterInput.getInstance(jasperPrintList);
		final OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(outputStream);
		ExporterConfiguration exporterConfiguration = null;

		Exporter exporter = null;
		if (isPDF) {
			exporter = new JRPdfExporter();
			exporterConfiguration = new SimplePdfExporterConfiguration();
		} else {
			exporter = new JRXlsExporter();
			exporterConfiguration = new SimpleXlsExporterConfiguration();

			final SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
			xlsReportConfiguration.setIgnoreGraphics(false);
			exporter.setConfiguration(xlsReportConfiguration);
		}

		exporter.setExporterInput(exporterInput);
		exporter.setExporterOutput(exporterOutput);
		exporter.setConfiguration(exporterConfiguration);

		return exporter;
	}
}
