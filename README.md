# nimble-survey-test

This is the project repositroy for simple survey.

It is mainly developed with kotlin using android studio. 

Firstly, when we open the application for the first time, we can see splash screen and then we will need to login to use the app.
> Please note it is for test project for nimble, it doesn't support registration feature so we can login by typing anything in username and password filed.
> If you download the repo from github and run the app, please this two in your local.properties file.

**CLIENT_ID="6GbE8dhoz519l2N_F99StqoOs6Tcmm1rXgda4q__rIw"
CLIENT_SECRET="_ayfIm7BeUAhx2W1OUqi20fwO3uNxfo1QstyKlFCgHw"**

Then, if we login successfully and get access token and refresh token, it will store in phone memory to use later when the access is expired and then to fetch refresh token by using authentication interceptor.

If we login successfully , we will see surveyslist home screen.
We can see list of survey by scrolling horizontal scrollview.

If we click on survey, it will take to survey detail screen.

If we successfully login to the application, the next time we use the application then we won't need to login again. We save the access token and refresh behind the scense and it will fetch new token when the token is expired.
