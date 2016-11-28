# Weekends and holidays table

First, you need data from http://data.gov.ru/opendata/7708660670-proizvcalendar
Производственный календарь

Then, you can run program:
```
    java -cp ~/code/weekends/target/scala-2.12/weekends-assembly-1.0.jar \
        ps.csv.weekends \
        /media/sf_t/data-20161107T1038-structure-20161107T1038.csv > weekends.lst 
```

To compile the program, use sbt:
    `sbt assembly` 
