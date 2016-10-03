# SENG2050 Assignment 3 - IT Services Portal

To clone the project, run this command in the terminal:

    git clone https://github.com/SENG2050/it-services-portal.git
    
# Getting Setup In IDEA

The project should open and build using the default settings provided. You can build the project using Build > Rebuild Project.

To run the project, first setup your run configuration by clicking on Run > Edit Configurations...

Click the green plus symbol, and then select Tomcat Server > Local.

- Set the Name to be 'Tomcat Server'
- Set the Application server to your installed version of Tomcat
- The Open browser settings can be left as default
- The VM options settings can be left as default
- The On 'Update' action settings can be left as default
- Set the JRE setting to the Default (1.7 - project SDK)
- The Tomcat Server Settings can be left as default

You will see a red alert box with a warning for no artifacts marked for deployment. Click on the Fix button to the right, and this will resolve this warning for you.

Then click on Apply and finally OK, and you can now run the project using Run > Run 'Tomcat Server'.

# Copying To Tomcat For Submission

All that is required to submit the final compiled project is to copy the ```out/artifacts/it_services_portal_war_exploded``` folder contents to the Timcat webapps folder, and rename it to ```c3063467_c3146986_c3198477_c3207772_FinalProject```.

Please note that this is not required to develop the project, as IDEA will take care of deploying Tomcat for you as above.