# EV_Charging_Point

This package contains the application itself. The emulator implements only the basic functionality necessary to simulate
the charging process

## Architecture

The charging station emulator is a multithreaded application, it implements:

* Communication with an electric vehicle model according to the Iso 15118 -2 protocol
* Communication with the OCPP J control system model
* Simulation of the physical charging process at the algorithmic level
* Station status display\
  The architecture of the program is modular, event-oriented, with the possibility of communication between different
  modules. This is implemented thanks to Spring Events (Spring Context library)

<div align="center">
     <img src=".\src\main\resources\images\Application_Architecture.jpg">
</div>
<div align="center">Application Architecture</div>

## Description of the implemented modules

* The communication module with an electric vehicle is a tcp server that implements interaction with an electric vehicle
  according to the ISO 15118 – 2 protocol.
* The module for communication with the charging station management system is a websocket client that implements the
  OCPP protocol, and more specifically its specification – OCPP Json or OCPP over websocket.
* The charging emulation module is a component of the program that emulates physical charging at the algorithm level –
  in particular, updating the energy meter over time. This module can run a task in a separate thread to update counter
  values (getting counter values by different threads in sync)
* The station state operator is a program component representing a finite state machine (using Spring StateMachine), it
  is a mathematical model describing the behavior of a system that can be in one of a finite number of states at a given
  time. This model consists of a set of states, transitions between states, and a set of rules that determine how the
  system reacts to input data and what transitions occur at these inputs.
* Connector manager is a module of the program responsible for unlocking the connector of the model (the connector is
  presented as an abstraction). The component uses the status of the station and possible other external data to make
  decisions about whether to unlock the connector or not.
* Since the architecture of communication modules with an electric vehicle and a control system is such that messages in
  these modules are collected only on the basis of received event objects from other modules, it was decided to process
  some messages and events into separate helper modules. These modules process messages that do not need to communicate
  with other modules of the application to process, and these modules also perform other auxiliary actions (for example,
  running in a separate stream of requests to get the meter value).

## How to expand it

Before describing the rules for extending the program, it is necessary to describe the common elements that all modules
in the project can use

### Сommon program components

* Abstract class **StationEvent** — The StationEvent class inherits from the class of the Spring Context –
  ApplicationEvent. ApplicationEvent serves as the main structural element for all classes of events in the application.
  This abstract class defines common properties and behaviors that are used by different types of events. Events
  encapsulate significant events in the system and provide communication between different components. StationEvent does
  not define any additional fields and methods and is a simple wrapper of the ApplicationEvent class. This is done in
  order to make it easier to log sending and receiving exactly the event objects defined by us in the application,
  without logging sending and receiving objects of internal event classes used in Spring libraries. All event classes
  defined in the program must inherit from the StationEvent class.

<div align="center">
     <img src=".\src\main\resources\images\StationEvent_Class.jpg">
</div>
<div align="center">StationEvent Class</div>

* Interface **StationEventListener<T extends StationEvent>** is a parameterized interface which are implemented, in the
  program, by classes listening for events. During implementation, it is necessary to specify as a parameter the class
  of the co-existence object that listens to the event listener class. Also, the class implementing this interface must
  be a Spring Component, and the redefined method must be marked with the @EventListener annotation.

<div align="center">
      <img src=".\src\main\resources\images\StationEventListener_Interface.jpg">
</div>
<div align="center">StationEventListener Interface</div>

* Abstract class **StationEventPublisher** is a class that implements the Spring interface
  ApplicationEventPublisherAware. This interface, by means of Spring, establishes a dependency on the implementation of
  the ApplicationEventPublisher interface through the setApplicationEventPublisher method. ApplicationEventPublisher is
  an interface in the Spring Framework that provides the ability to publish events in the context of an application. It
  is part of the event engine in Spring and is used to send event messages that can be handled by other components of
  the application. The applicationEventPublisher field is represented as a protected field referring to the
  implementation of the ApplicationEventPublisher interface.
  The class must inherit StationEventPublisher if it needs to publish any events. To do this, use the
  ApplicationEventPublisher field and its publishEvent(StationEvent event) method (specify an object of the StationEvent
  class as the method argument).

<div align="center">
      <img src=".\src\main\resources\images\StationEventPublisher_Class.jpg">
</div>
<div align="center">StationEventPublisher Class</div>

### Rules for adding new modules

* Each module must contain a core class that contains some kind of logic
* Each module must provide its own API that implements the core class.
* Each module must have classes that listen for events, these classes must be dependent on the API of the main class of
  the module and implement the StationEventListener interface, specify the event class that it listens to as a
  parameter.
* Each module may include a class or classes that publish events. These classes must inherit StationEventPublisher. In
  some cases, the core class of the module may be the event publisher and inherit from the StationEventPublisher class.

<div align="center">
       <img src=".\src\main\resources\images\Generalized_Module_Architecture.jpg">
</div>
<div align="center">Generalized module architecture</div>