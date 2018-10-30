/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author ove
 */
public class GeneradorReportes {

    public static void generarReporte(JRBeanCollectionDataSource dataSource,
            HashMap<String, Object> parameters, String exportaComo, String reporte) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();

            ServletContext sContext = (ServletContext) facesContext.getExternalContext().getContext();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    sContext.getRealPath("/reportes/" + reporte + ".jasper"), parameters, dataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) 
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", 
                    "attachment; filename=report." + exportaComo);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

            if (exportaComo.equals("xlsx")) {
                JRXlsxExporter docxExporter = new JRXlsxExporter();
                docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
                docxExporter.exportReport();
            } else if (exportaComo.equals("pdf")) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            }

        } catch (JRException | IOException ex) {
            Logger.getLogger(GeneradorReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
