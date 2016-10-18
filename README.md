# SENG2050 Assignment 3 - IT Services Portal

To clone the project, run this command in the terminal:

    git clone --recursive https://github.com/SENG2050/it-services-portal.git

You will be prompted for credentials for Bitbucket, which we have used to keep the proprietary theme closed-source to stay within the theme's licensing guidelines. To get access to the private repository contact [Mitchell Davis](https://github.com/mitchdav).

## Configuring The System

A Java ```config.properties``` file needs to be created and set with an appropriate ```baseURL``` value in the ```/WEB-INF/classes/config/``` folder.

An example file has been added to ```src/config/config.properties.example``` which can be directly copied to ```src/config/config.properties``` during development.

## Getting Setup In IDEA

The project should open and build using the default settings provided. You can build the project using Build > Rebuild Project.

To run the project, first setup your run configuration by clicking on Run > Edit Configurations...

Click the green plus symbol, and then select Tomcat Server > Local.

- Set the Name to be 'Tomcat Server'
- Set the Application server to your installed version of Tomcat
- The Open browser settings can be left as default
- The VM options settings can be left as default
- The On 'Update' action settings can be left as default
- Set the JRE setting to the Default (1.8 - project SDK)
- The Tomcat Server Settings can be left as default

You will see a red alert box with a warning for no artifacts marked for deployment. Click on the Fix button to the right, and this will resolve this warning for you.

Then click on Apply and finally OK, and you can now run the project using Run > Run 'Tomcat Server'.

## Copying To Tomcat For Submission

All that is required to submit the final compiled project is to copy the ```out/artifacts/it_services_portal_war_exploded``` folder to the Tomcat webapps folder, and rename it to ```c3063467_c3146986_c3198477_c3207772_FinalProject```.

Finally, update the ```/WEB-INF/classes/config/config.properties``` file to have ```baseURL=http\://localhost\:8080/c3063467_c3146986_c3198477_c3207772_FinalProject/``` (trailing slash is required).

Please note that this is not required to develop the project, as IDEA will take care of deploying Tomcat for you as above.

## Routes

| Requires Login | User Type | Methods   | Route                 | Controller              | JSP Path (inside /WEB-INF/jsp) | Purpose                                                                                              |
|----------------|-----------|-----------|-----------------------|-------------------------|--------------------------------|------------------------------------------------------------------------------------------------------|
| No             | Any       | GET       | /                     | IndexController         | N/A                            | Redirects to /kb                                                                                     |
| No             | Any       | GET       | /kb                   | KB_IndexController      | /kb/index.jsp                  | Displays all knowledge base (KB) articles and a search bar                                           |
| No             | Any       | GET       | /kb/search?term=*     | KB_SearchController     | /kb/search.jsp                 | Searches all KB articles for the given term and displays titles and links to them, with a search bar |
| No             | Any       | GET       | /kb/suggest?term=*    | KB_SuggestController    | N/A                            | Searches all KB articles for the given term and returns them in a JSON structure                     |
| No             | Any       | GET       | /kb/{id}              | KB_ViewController       | /kb/view.jsp                   | Allows the user to view the given KB article                                                         |
|                |           |           |                       |                         |                                |                                                                                                      |
| No             | Any       | GET, POST | /login                | LoginController         | /login.jsp                     | Allows the user to login, and then redirect to their previous page                                   |
| Yes            | Any       | GET       | /logout               | LogoutController        | N/A                            | Logs the user out, and then redirects to /                                                           |
|                |           |           |                       |                         |                                |                                                                                                      |
| Yes            | Admin     | GET       | /issues               | Issues_IndexController  | /issues/index.jsp              | Shows the admin a list of all issues                                                                 |
| Yes            | Admin     | GET       | /issues/search?term=* | Issues_SearchController | /issues/search.jsp             | Searches all issues for the given term and displays titles and links to them, with a search bar      |
| Yes            | Admin     | GET, POST | /issues/{id}          | Issues_ViewController   | /issues/view-admin.jsp         | Allows the admin to view the given issue, change state, add comments, mark as KB article, etc        |
|                |           |           |                       |                         |                                |                                                                                                      |
| Yes            | User      | GET, POST | /issues/new           | Issues_NewController    | /issues/new.jsp                | Allows the user to create a new issue                                                                |
| Yes            | User      | GET, POST | /issues/{id}          | Issues_ViewController   | /issues/view-user.jsp          | Allows the user to view the given issue if they created it, add comments                             |
