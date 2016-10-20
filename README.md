#IntentService

IntentService is a base class for Services that handle asynchronous requests (expressed as Intents) on demand. Clients send requests
through startService(Intent) calls; the service is started as needed, handles each Intent in turn using a worker thread, and stops itself 
when it runs out of work.

It doesn't run in background if the app is killed

Main Thread-UI Thread
WorkerThread - Background Thread

	
####Difference b/w AsyncTask and IntentService
AsyncTask is used for short period operation and dependent of activity
IntentService is used for long running operation and independent of activity 
