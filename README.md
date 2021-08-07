# Sleepy

This project uses the sleep API, which is an Android Activity Recognition API that surfaces information about the user’s sleep. It can be used to power features like the Bedtime mode in Clock.

This sleeping information is reported in two ways:

- A ‘sleep confidence’, which is reported at a regular interval (up to 10 minutes)
A daily sleep segment which is reported after a wakeup is detected
- The API uses an on-device artificial intelligence model that uses the device’s light and motion sensors as inputs.

As with all of our Activity Recognition APIs, the app must be granted the Physical Activity Recognition runtime permission from the user to detect sleep.
