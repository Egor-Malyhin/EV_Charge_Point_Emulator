# V2G_Protocol_Messages
During the development, it was decided to take out all classes of messages of the V2G protocol (the application layer protocol defined in ISO 15118 - 2), all builders and factories of objects of these classes, as well as the class converting these objects to XML and EXI into a separate library. The code of this library was included in the executive jar files of the charging station emulator and the EVCC model.