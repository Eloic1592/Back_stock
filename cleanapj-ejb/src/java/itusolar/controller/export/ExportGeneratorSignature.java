package itusolar.controller.export;

import bean.ClassMAPTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 * The interface Export generator signature.
 */
public interface ExportGeneratorSignature {
    /**
     * Export.
     *
     * @param object     the object
     * @param request    the request
     * @param response   the response
     * @param where      the where
     * @param connection the connection
     * @throws ExportFoundException when the ExportGenerator found an appropriate extension, Exception the exception
     */
    public void export(ClassMAPTable object, HttpServletRequest request, HttpServletResponse response, String where, Connection connection) throws Exception;

    /**
     * Export.
     *
     * @param object     the object
     * @param request    the request
     * @param response   the response
     * @param params     the params
     * @param connection the connection
     * @throws Exception the exception
     */
    public void export(ClassMAPTable object, HttpServletRequest request, HttpServletResponse response, ExportParams params, Connection connection) throws Exception;
}
