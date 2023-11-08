# nimble-survey-test

This is the test project repositroy for simple survey.

It is mainly developed with kotlin using android studio. 

Firstly when app open user will see splash screen and if the user use the app for the first time then user will need to login to use the app.
> Please note it is for test project for nimble, it doesn't support registration feature so user can login by typing anything in username and password filed.

Then, if we login successfully and get access token and refresh token, it will store in phone memory to use later when the access is expired and then to fetch refresh token by using authentication interceptor.

If we login successfully , user will see surveyslist home screen.
User can see list of survey by scrolling horizontal scrollview.

If user click on survey, it will take to survey detail screen.

If user successfully login to the app, the next time we use the app it won't need to login again. We save the access token and refresh behind the scense and it will fetch new token when the token is expired.
