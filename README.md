# _Band Tracker_

#### _Code Review for 5.13.16_

#### _By Joshua Logan_

## _Description_

_This is a java web app that allows a user to enter band into a DB and then allow a user to add a venue and track which venues a band has played._

## Setup

Clone this repository:
```
$ cd ~/Desktop
$ git clone https://github.com/vladtheinhaler23/4.13.16-Code-Review-Band-Tracker.git
$ cd CLONED_DIRECTORY
```

Open terminal and run Postgres:
```
$ postgres
```

Open a new tab in terminal and create the `band_tracker` database:
```
$ psql
$ CREATE DATABASE band_tracker;
$ psql band_tracker < band_tracker.sql
```

Navigate back to the directory where this repository has been cloned and run gradle:
```
$ gradle run
```

## Known Bugs

_No known bugs_

## Support and contact details

_If you need help please check the Contact me at joshlogan32323@gmail.com_

## Technologies Used

_Java, Spark, JUnit, Apache, Velocity, FluentLenium, SQL_

### License

*This webpage was created under the MIT license*

Copyright (c) 2016 **_Joshua Logan_**
