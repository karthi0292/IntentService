IntentService is a base class for Services that handle asynchronous requests (expressed as Intents) on demand. Clients send requests
through startService(Intent) calls; the service is started as needed, handles each Intent in turn using a worker thread, and stops itself 
when it runs out of work.

It doesn't run in background if the app is killed
