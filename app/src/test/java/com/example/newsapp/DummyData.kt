package com.example.newsapp

object DummyData {
    const val LOGIN_200 = "{\n  \"token\": \"somerandomtoken\",\n  \"scheme\": \"Bearer\",\n  \"expires_at\": \"2019-08-24T14:15:22Z\"\n}"
    const val BODY_401 = "{\n\"message\": \"error-message-from-server\"\n}"
    const val PROFILE_200 = "{\n\"username\": \"username\",\n\"name\": \"John Doe\",\n\"bio\": \"Lorem ipsum dolor si jamet\",\n\"web\": \"https://example.com\",\n\"picture\": \"https://img.example.com/profile.jpg\"\n}"
    const val NEWS_200 = "{\n\"data\": [\n{\n\"id\": 1,\n\"title\": \"Sample Title\",\n\"url\": \"https://example.com\",\n\"cover_image\": \"https://place-hold.it/600x400/aaa/000000.jpg&text=SampleImage&bold&fontsize=20\",\n\"nsfw\": true,\n\"channel\": {\n\"id\": 2,\n\"name\": \"r/sample\"\n},\n\"counter\": {\n\"upvote\": 2,\n\"downvote\": 3,\n\"comment\": 4,\n\"view\": 5\n},\n\"created_at\": \"2019-08-24T14:15:22Z\"\n}\n]\n}"
}