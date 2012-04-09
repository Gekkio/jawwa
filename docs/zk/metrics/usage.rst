Usage
=====

The module collects metrics automatically once the ZK application starts. All metrics are viewable with JMX (e.g. with jconsole).

Currently the module collects the following metrics:

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,name=activeDesktops*
  Tracks the count of active ZK desktops.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,name=activeEvents*
  Tracks the count of events being processed.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,name=activeSessions*
  Tracks the count of active ZK sessions.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,name=activeUpdates*
  Tracks the count of active ZK AJAX updates.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,name=clientRequestCompletedDuration*
  Measures the combined duration of server-side request processing, network latency, and client-side processing.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,name=clientRequestReceivedDuration*
  Measures the combined duration of server-side request processing and network latency.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,scope=desktops,name=desktopsCreated*
  Counts the number of desktops that have been created.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,scope=desktops,name=desktopsDestroyed*
  Counts the number of desktops that have been destroyed.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,name=eventProcessingDuration*
  Measures the duration of event processing work.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,scope=events,name=eventsPosted*
  Counts the number of events that have been posted (Events.postEvent).

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,scope=events,name=eventsProcessed*
  Counts the number of events that have been processed.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,scope=events,name=eventsSent*
  Counts the number of events that have been sent (Events.sendEvent).

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,name=serverRequestCompletedDuration*
  Measures the durations of server-side request processing.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,scope=sessions,name=sessionsCreated*
  Counts the number of sessions that have been created.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,scope=sessions,name=sessionsDestroyed*
  Counts the number of sessions that have been destroyed.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,scope=updates,name=updatesProcessed*
  Counts the number of AJAX updates that have been processed.

*fi.jawsy.jawwa.zk.metrics:type=ZkMetrics,scope=updates,name=updatesStarted*
  Counts the number of AJAX updates that have been started.
