## install tomcat

- `sudo apt install tomcat9 tomcat9-admin`
- `sudo systemctl enable tomcat9`
- allow traffic to 8080 : `sudo ufw allow from any to any port 8080 proto tcp`

- Check here : `http://127.0.0.1:8080`

```
It works !
If you're seeing this page via a web browser, it means you've setup Tomcat successfully. Congratulations!

This is the default Tomcat home page. It can be found on the local filesystem at: /var/lib/tomcat9/webapps/ROOT/index.html

Tomcat veterans might be pleased to learn that this system instance of Tomcat is installed with CATALINA_HOME in /usr/share/tomcat9 and CATALINA_BASE in /var/lib/tomcat9, following the rules from /usr/share/doc/tomcat9-common/RUNNING.txt.gz.

You might consider installing the following packages, if you haven't already done so:

tomcat9-docs: This package installs a web application that allows to browse the Tomcat 9 documentation locally. Once installed, you can access it by clicking here.

tomcat9-examples: This package installs a web application that allows to access the Tomcat 9 Servlet and JSP examples. Once installed, you can access it by clicking here.

tomcat9-admin: This package installs two web applications that can help managing this Tomcat instance. Once installed, you can access the manager webapp and the host-manager webapp.

NOTE: For security reasons, using the manager webapp is restricted to users with role "manager-gui". The host-manager webapp is restricted to users with role "admin-gui". Users are defined in /etc/tomcat9/tomcat-users.xml.
```

- `sudo nano /etc/tomcat9/tomcat-users.xml`
- add user :

```xml
<role rolename="admin-gui"/>
<role rolename="manager-gui"/>
<user username="tomcat" password="pass"roles="admin-gui,manager-gui"/>
```

- restart : `sudo systemctl restart tomcat9`

- Check : `http://127.0.0.1:8080/manager/html`

## Create a project

- Open intellij
- Select New project
- Select `Java Enterprise`
- Project template : `REST service`
- Build system : `maven`
- Testing : `JUnit`
- Next
- Select Dependencies :

  - Contexts and Dependency Injection
  - RESTful Web Services (JAX-RS)
  - Servlet

- Finish

## install glassfish

- run : `sudo /opt/glassfish5/bin/asadmin start-domain`
