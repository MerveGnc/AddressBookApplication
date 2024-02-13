<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Person" %>
<%@ page import="dataAccess.PersonDao" %>
<%@ page import="dataAccess.InMemoryPersonDao" %>

<%
    PersonDao personDao = new InMemoryPersonDao();
    List<Person> personList = personDao.getAllPersons();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kişi Listesi</title>
    <style>
        body {
            background-color: powderblue;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 60vh;
        }
        .container {
            text-align: center;
        }
        h1 {
            color:LightSeaGreen;
        }
         th {
            background-color:LightSeaGreen ;
            color: white;
            padding: 10px;
        }
        
        td {
            padding: 10px;
            color: LightSeaGreen;
            
        }
        label {
            color: LightSeaGreen;
        }
        p{
            color:Teal;
        }
    </style>
</head>
<body>
 <div class="container">
    <h1>Kişi Listesi</h1>
    <table>
        <thead>
            <tr>
                <th>İsim</th>
                <th>Soyad</th>
                <th>Telefon</th>
                <th>Adres</th>
                <th>Yaş</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
           <% for (entity.Person person : (List<entity.Person>) request.getAttribute("personList")) { %>
                <tr>
                    <td><%= person.getName() %></td>
                    <td><%= person.getSurname() %></td>
                    <td><%= person.getPhone() %></td>
                    <td><%= person.getAddress() %></td>
                    <td><%= person.getAge() %></td>
                   <td><a href="personServlet?id=<%= person.getId() %>&_method=DELETE">Delete</a></td>
                </tr>
            <% } %>

        </tbody>
    </table>
    <br>
    <p>Yeni kişi eklemek veya mevcut kişiyi güncellemek için<a href="addPerson.xhtml"> tıklayınız.</a></p>
    <p>Çıkış yapmak için <a href="address.xhtml">tıklayınız.</a></p>
     </div>
</body>
</html>
