package server;

import java.util.*;
import javax.servlet.http.*;

public abstract class AbstractServlet extends HttpServlet 
{
    /**
     * Certifica de que todos os parâmetros passados estão presentes na request
     * 
     * @param request Requisição HTTP
     * @param parameters Parâmetros a serem verificados
     * @throws Exception caso algum dos parâmetros não exista na requisição
     */
    protected void assertParameters(HttpServletRequest request, String... parameters)
            throws Exception
    {
        Map<String, String[]> parameterMap = request.getParameterMap();
        
        for (String param : parameters)
            if (!parameterMap.containsKey(param))
                throw new Exception("Parâmetro não encontrado: " + param);
    }
}
