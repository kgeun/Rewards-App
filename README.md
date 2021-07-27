# Sky Rewards

Sky Take Home Assignment, Kyung Geun Lee  
24th ~ 27th July 2021  

## Introduction
Sky Rewards is an Android native app that tells customers who have been using Sky for a long time if they are eligible for reward by entering their subscription channel and account number.  

## Screenshots

![KakaoTalk_Photo_2021-07-27-오후 6-26-39](https://user-images.githubusercontent.com/7823937/127200179-204de78a-1e00-4f3b-9f43-1afc2afdf4f2.jpeg) ![KakaoTalk_Photo_2021-07-27-오후 6-25-38 001](https://user-images.githubusercontent.com/7823937/127200213-8dd7870d-9607-4c34-8c0c-7304203879ea.jpeg) ![KakaoTalk_Photo_2021-07-27-오후 6-25-38 002](https://user-images.githubusercontent.com/7823937/127200378-6e8dc2df-4cc0-4bc5-b797-a4ddbf7759c3.jpeg)  
![KakaoTalk_Photo_2021-07-27-오후 6-25-40 004](https://user-images.githubusercontent.com/7823937/127200411-38aac5f9-3e4d-4f3f-a048-124caab68e6c.jpeg) ![KakaoTalk_Photo_2021-07-27-오후 6-25-42 005](https://user-images.githubusercontent.com/7823937/127200439-da147337-5f52-4645-ad9d-a18c11b7a9e6.jpeg)


## How to use the app
1. You can download APK file here : https://drive.google.com/drive/folders/1ChExbsmyO0zOGH-CIZIMrq132mBjGiak?usp=sharing  
2. Please download and install the SkyRewards_live_release_1.0_1_20210727.apk file.  
3. You can proceed according to the steps instructed by the app. Here are the expected result values based on the value you enter:  

Channel | Account Number Validity | Result
------------ | --------------------- | ---------------------------
Sky Sports | Valid | Get Reward (Champions League Final Ticket)
Sky Sports | Invalid | Invalid Account Number
------------ | --------------------------- | ---------------------------
Sky Kids | Valid | Ineligible
Sky Kids | Invalid | Ineligible
------------ | --------------------------- | ---------------------------
Sky Music | Valid | Get Reward (Karaoke Pro Microphone)
Sky Music | Invalid | Invalid Account Number
------------ | --------------------------- | ---------------------------
Sky News | Valid | Ineligible
Sky News | Invalid | Ineligible
------------ | --------------------------- | ---------------------------
Sky Music | Valid | Get Reward (Pirates of Caribbean Collection)
Sky Music | Invalid | Invalid Account Number
   
### Valid Account Number Criteria
**If the first four digits are all the same, it is judged to be Valid**  


## Source Repository
https://bitbucket.org/freebuds/sky-rewards/src/master/  
## Project Kanban Board
https://trello.com/b/liirAZJZ/sky-rewards  
  
## Tech stacks

- Language : **Kotlin**  
- **MVVM Architecture, LiveData, DataBinding, Room** : The architecture needed to be applied in the future, considering scalability, and these were the choice.  
- **Hilt** : For clean architecture application, used Hilt, which is Google's official library and much simpler to use than Dagger.  
- **Android Navigation Component** : Chosen to quickly construct multi-step screens and animation  
- **Bound Services** : Selected to mock the backend server. I chose it because it was similar to the behavior of the backend in that it should always be simulated independently of the behavior of the client.  
- **Glide** : Only local images were used, but selected for optimal image processing and scalability.  
- **Moshi** : Apply json serialization library to mock communication with backend  
- **Firebase** : Firebase library has been applied to check application indicators such as Crashlytics and Event and for applying additional features in the future  


## Introduction to the project focus
### Easily extensible design
1. Separation of client-server logic by making the backend logic independent as a bound service part to be similar to reality
2. By simulating actual client to server communication, the information input by the user and the reward information sent by the server are all encapsulated in a data class and designed to communicate in a byte array. It is designed not to change the communication protocol even if the structure changes.
3. The settings set by the company (channels, rewards, service error output, etc.) are all managed in one location (constants directory or package in the project) affecting the entire project.      
### Object-oriented and test-aware design
1. In the RewardsService and Eligible Service, which are the core algorithms of the service, the service stage and the actual logic part are separated and designed to increase the test efficiency.      
### Points that are close to the actual service
1. It has a beautiful and practical UX design that looks like using Sky's real app.
2. By applying scrolling to each screen, you can see the entire content even on a small screen.
3. Separated the flavor for the test version and the live version, and created an app signing keystore file. (total 4 flavors : qaDebug, qaRelease, liveDebug, liveRelease)
4. You can see the statistics used by actual users by applying the Firebase Crashes Library and each click and screenview indicator.


## Test Cases and Results (Unit Test through JUnit4)
1. Testing for user input

> Input : `Customer_who_subscribed_sports_channel_and_have_valid_account_number`  
> Result : **Pass**  
    
> Input : `Customer_who_subscribed_sports_channel_and_have_invalid_account_number`  
> Result : **Pass**
      
> Input : `Customer_who_subscribed_kids_channel_and_have_valid_account_number`  
> Result : **Pass**
      
> Input : `Customer_who_subscribed_kids_channel_and_have_invalid_account_number`  
> Result : **Pass**
      
> Input : `Customer_who_subscribed_music_channel_and_have_valid_account_number`  
> Result : **Pass**
      
> Input : `Customer_who_subscribed_music_channel_and_have_invalid_account_number`  
> Result : **Pass**
      
> Input : `Customer_who_subscribed_news_channel_and_have_valid_account_number`  
> Result : **Pass**
      
> Input : `Customer_who_subscribed_news_channel_and_have_invalid_account_number`  
> Result : **Pass**
      
> Input : `Customer_who_subscribed_movie_channel_and_have_valid_account_number`  
> Result : **Pass**
      
> Input : `Customer_who_subscribed_movie_channel_and_have_invalid_account_number`  
> Result : **Pass**
      
2. Testing for Room Database and ViewModel
      
> Input : Customer Data  
> Result : **Pass**
      
> Input : Rewards Result  
> Result : **Pass**
      
**Total** : 12 cases  
**Pass**: 12 cases  
**Fail** : 0 cases  
  
## Copyright

All images and resources used in the project were personal or commercially licensed.  
Vector images were downloaded from freepik.com.  