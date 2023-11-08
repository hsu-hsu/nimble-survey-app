# nimble-survey-test

This is the project repositroy for simple survey.

It is mainly developed with kotlin using android studio. 

Firstly, when we open the application for the first time, we can see splash screen and then we will need to login to use the app.
> Please note it is for test project for nimble, it doesn't support registration feature so we can login by typing anything in username and password filed.

Then, if we login successfully and get access token and refresh token, it will store in phone memory to use later when the access is expired and then to fetch refresh token by using authentication interceptor.

If we login successfully , we will see surveyslist home screen.
We can see list of survey by scrolling horizontal scrollview.

If we click on survey, it will take to survey detail screen.

If we successfully login to the application, the next time we use the application then we won't need to login again. We save the access token and refresh behind the scense and it will fetch new token when the token is expired.
