package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.InMemoryPersonDao;
import dataAccess.PersonDao;
import entity.Person;

@WebServlet("/personServlet")
public class PersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonDao personDao;
    
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = getServletContext();
        personDao = (PersonDao) servletContext.getAttribute("personDao");

        if (personDao == null) {
            personDao = new InMemoryPersonDao();
            servletContext.setAttribute("personDao", personDao);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");

        if (method != null && method.equals("DELETE")) {
            String idParam = request.getParameter("id");
            int id = Integer.parseInt(idParam);
            deletePerson(id);
            response.sendRedirect(request.getContextPath() + "/personServlet");
        } else {
            String idParam = request.getParameter("id");
            int id = idParam != null ? Integer.parseInt(idParam) : 0; 

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            int age = Integer.parseInt(request.getParameter("age"));

            boolean savedOrUpdated = saveOrUpdatePerson(id, name, surname, phone, address, age);

            if (savedOrUpdated) {
                List<Person> personList = personDao.getAllPersons();
                request.setAttribute("personList", personList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("personList.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("error", "Kişi kaydedilemedi veya güncellenemedi!");
                request.getRequestDispatcher("addPerson.xhtml").forward(request, response);

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");

        if (method != null && method.equals("DELETE")) {
            String idParam = request.getParameter("id");
            int id = Integer.parseInt(idParam);
            deletePerson(id);
            response.sendRedirect(request.getContextPath() + "/personServlet");
        } else {
            List<Person> personList = personDao.getAllPersons();
            request.setAttribute("personList", personList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("personList.jsp");
            dispatcher.forward(request, response);
        }
    }

    private boolean saveOrUpdatePerson(int id,String name, String surname, String phone, String address, int age) {
        Person existingPerson = personDao.getPersonByNameAndSurname(name, surname);

        if (existingPerson != null) {
            existingPerson.setPhone(phone);
            existingPerson.setAddress(address);
            existingPerson.setAge(age);
            personDao.updatePerson(existingPerson);
            return true;
        } else {
            Person newPerson = new Person(id,name, surname, phone, address, age);
            personDao.addPerson(newPerson);
            return true;
        }
    }

    private void deletePerson(int id) {
        Person person = personDao.getPersonById(id);
        if (person != null) {
            personDao.deletePerson(person.getId());
        }
    }
}
