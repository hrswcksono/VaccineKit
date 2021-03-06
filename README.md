# B21-CAP0255 - VaccineKit


VaccineKit is an application that will help certain institutions or professionals recognize every citizen who has had vaccines by scanning the barcode on the bracelet which will be given later. Currently, the vaccination data is only stored in the vaccination certificate’s barcode. Our team wanted to make it even more interesting by using a bracelet because it was easy to carry. In addition, in the future, this bracelet is also expected to be detected for other purposes. 
    
Our planned execution of this project is to create a chat bot, face recognition for verification when someone loses the band, a mobile app that can be accessed by users to get information about health especially covid and scan barcodes from the band and save the dataset in the cloud, monitoring, and logging. 

After working for approximately one month our team can make the face recognition, two apps for user and heath institution include scan barcode and access vaccination data.


### Built With

* [Android Studio Native](https://developer.android.com/studio)
* [Firebase Firestore](https://firebase.google.com/docs/firestore)
* [Firebase Storage](https://firebase.google.com/docs/storage)
* [Firebase Auth](https://firebase.google.com/docs/auth)
* [Kotlin Programming Language](https://kotlinlang.org/)
* [Retrofit](https://square.github.io/retrofit/)
* [OkHttp](https://square.github.io/okhttp/)
* [Koin](https://insert-koin.io/)
* [Coroutine Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/)
* [Budiyev Scanner](https://github.com/yuriy-budiyev/code-scanner)
* [JourneyApps](https://github.com/journeyapps/zxing-android-embedded)


### Screenshot Apps

#### Institution Apps

<img src="https://user-images.githubusercontent.com/57925757/121060173-7ee3ce00-c7ec-11eb-8fcc-cb7b19072edc.jpg" height="400" width="650" >

<img src="https://user-images.githubusercontent.com/57925757/121060177-8014fb00-c7ec-11eb-8b5d-6d5d6d33aa4f.jpg" height="400" width="650" >


#### User Apps

<img src="https://user-images.githubusercontent.com/57925757/121058264-58bd2e80-c7ea-11eb-83c9-c10fda6513f1.jpg" height="400" width="650" >

<img src="https://user-images.githubusercontent.com/57925757/121058258-565ad480-c7ea-11eb-8e3a-ea41491c328f.jpg" height="400" width="650" >

### Download APK
* [Download](https://drive.google.com/drive/folders/1bQDELZDOr4DviqBM4m7H_vdcRlNdTzfs?usp=sharing)

<!-- GETTING STARTED -->
### USAGE
#### Institution Apps
1. Institution user have to registered in firebase auth
2. Login to your institution account
##### Features
    Registration for User
    1. Input personal data user
    2. Take 15 photo
    Verify is User
    1. Scan Barcode from app user
    2. Show personal data user

#### User Apps
1. User have to registered by Institution User
2. Login to your user account
##### Features
    Profile User
    1. Show personal data user
    Lose Band -> Request New Band
    1. Take 1 photo to predict
    2. If yes will show the new barcode, if no show alert
