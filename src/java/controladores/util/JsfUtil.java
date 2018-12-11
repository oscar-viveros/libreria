package controladores.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 * Clase de utilidad para los JSF
 * 
 * @author Oscar Viveros Egass
 */
public class JsfUtil {

    private static final String CORREO_DE = "banco.adsi@gmail.com";
    private static Properties properties;
    
    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    /**
     * Envía un correo electrónico a partir de los parámetros especificados. 
     * 
     * @param para el correo del destinatario
     * @param asunto el asunto del correo
     * @param correo el contenido del correo
     */
    public static void sendMail(String para, String asunto, String correo) {

        try {
            properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.auth", "true");

            javax.mail.Session session = javax.mail.Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(CORREO_DE, "ProyectoBanco");
                        }
                    });

            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(CORREO_DE));
            mensaje.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(para));
            mensaje.setSubject(asunto);
            mensaje.setContent("<h3>" + correo + "</h3>", "text/html");
            Transport.send(mensaje, mensaje.getAllRecipients());
        } catch (MessagingException ex) {
            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static <T> List<T> copiarIterator(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext()) {
            copy.add(iter.next());
        }
        return copy;
    }
    
    /**
     * Genera un reporte (XLSX o PDF) a partir de los parámetros especificados. 
     * 
     * @param dataSource la fuende de datos del reporte
     * @param parameters los parámetros necesarios para el reporte
     * @param exportaComo PDF o EXCEL 
     * @param reporte el nombre del archivo .jasper a generar
     */
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
            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
