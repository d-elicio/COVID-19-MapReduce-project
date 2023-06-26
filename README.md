# Covid-19 MapReduce project 
![GitHub watchers](https://img.shields.io/github/watchers/d-elicio/COVID-19-MapReduce-project?style=social) 
![GitHub forks](https://img.shields.io/github/forks/d-elicio/COVID-19-MapReduce-project?style=social)
![GitHub Repo stars](https://img.shields.io/github/stars/d-elicio/COVID-19-MapReduce-project?style=social)
![GitHub last commit](https://img.shields.io/github/last-commit/d-elicio/COVID-19-MapReduce-project?style=plastic)

Design and implementation of different simple MapReduce jobs used to analyze a dataset on Covid-19 disease created by [Our World In Data](https://ourworldindata.org/).

Design and implementation in MapReduce of: 
-  a job returning the classification of **continents** in order of *decreasing* **total_cases**
- a job returning, for each **location**, the *average* number of **new-tests** per day
- a job returning the *5 days* in which the total number of patients in Intensive care units (ICUs) and hospital was highest (**icu_patients+ hosp_patients**), by *decreasing order* of this total number.

All the MapReduce jobs will be executed with **different size of input values** and with **different
Hadoop configurations** (*local, pseudo-distributed without YARN and pseudo-distributed with YARN*) to evaluate the different execution times.

## 🚀 About Me
I'm a computer science Master's Degree student and this is one of my university project. 
See my other projects here on [GitHub](https://github.com/d-elicio)!

[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://d-elicio.github.io)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/domenico-elicio/)


# 💻 The project

### Dataset
The dataset is a collection of the COVID-19 data manteined and updated daily by *Our World In Data* and contains data on confirmed cases, deaths, hospitalizations, and other variables of potential interest. 

The dataset available in different formats can be found [here](https://github.com/owid/covid-19-data/tree/master/public/data), while the *data dictionary* useful to understand the meaning of all the dataset's columns is available [here](https://github.com/owid/covid-19-data/blob/master/public/data/owid-covidcodebook.csv).

![Data dictionary](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/127dad45-8243-40d2-80d4-6f0a0fa64d41)

## Job 1
**Implementation of a MapReduce job that returns the classification of **continents** in order of decreasing **total_cases**.**

Before the Map phase there is a **Splitting** phase where the raw lines of data contained in the dataset
are divided in “single blocks of data” using the “**,**” value to separate all the different values and the “**\\t**” value to separate all the different rows of the dataset. 

#### **MAPPING PHASE**

__*Input:*__ all the values contained inside each row of the dataset and separated by "**,**"

__*Output:*__ continent of each nation and the number of total cases (take only the information on the last day of the month (*e.g. if your input data contains info only on March-April period, to take the number of total cases you have to take only the info on 30th of April, because the number of total_cases in the dataset is a cumulative number based on the sum of the cases on the previous days*))

#### **SHUFFLING PHASE**

The MapReduce framework processes the output of the
map function before sending it to the reducer function. Map outputs are divided into groups.

__*Input:*__ output of the map function

__*Output:*__ each continent appear with a list of all of its total_cases

#### **REDUCE PHASE**

__*Input:*__ output of the *shuffling phase*

__*Output:*__ total number of cases for each continent

#### **FINAL RESULT**

The final result is composed by a *key-value* pair made of **KEY** = name of continent, **VALUE** = number of total cases for that continent.

At the end there will be a *sorting* of this key-value pairs to show the results in order of decreasing of
total cases.

![Job 1](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/39577901-cbd8-46ab-a47c-accf4fdb6edb)

### Job 1 results:
The execution of this job has been done in all 3 Hadoop configurations (*Local and Pseudo-distributed, __with__ and __without__ __YARN__*). 

*Three different types of input data* have been used. Results changes with respect to the inputs:

#### **Input: MARCH-APRIL data**
![immagine](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/b19f8ea4-3ff3-43fe-9b25-8f10068e2845)

#### **Input: MARCH-AUGUST data**
![immagine](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/e4497c5d-9809-4a9a-bc23-4cc2c35babfc)

#### **Input: MARCH-OCTOBER data**
![immagine](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/9bc50f84-13dd-4404-b190-a1d88382b88b)




## Job 2
**Implementation of a MapReduce job that returns for each **location** the average number of **new-tests** per day**

#### **MAPPING PHASE**

__*Input:*__ all the values contained inside each row of the dataset and separated by "**,**"

__*Output:*__ *location* and number of *new_tests* per day

#### **SHUFFLING PHASE**


__*Input:*__ output of the map function

__*Output:*__ each *location* appear with a list of all of its *new_tests*

#### **REDUCE PHASE**

__*Input:*__ output of the *shuffling phase*

__*Output:*__ total number of *new_tests* for each location and divide the result for the number *n* of days

#### **FINAL RESULT**

The final result is composed by a *key-value* pair made of **KEY** = name of location, **VALUE** = average number of *new_tests* for each location.

![immagine](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/2c14d3da-69ce-4b02-a27a-0a880ccdf4ed)


### Job 2 results:
The execution of this job has been done in all 3 Hadoop configurations (*Local and Pseudo-distributed, __with__ and __without__ __YARN__*). 

*Three different types of input data* have been used. Results changes with respect to the inputs:

#### **Input: MARCH-APRIL data**
![marapr](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/4db80cea-57da-491c-a4bd-fac62b98e921)

#### **Input: MARCH-AUGUST data**
![maraug](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/23ca7c17-7f7c-4a98-ab67-137e3b772f8d)

#### **Input: MARCH-OCTOBER data**
![marott](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/92010ce8-3049-44da-bbc4-4c3cad033a9a)


## Job 3
**Implementation of a MapReduce job that returns the classification of the **5 days** in which the total number of patients in Intensive Care Unit (ICUs) and hospital (**icu_patients + hosp_patients**) was highest, by decreasing order of this total number**

#### **MAPPING PHASE**

__*Input:*__ all the values contained inside each row of the dataset and separated by "**,**"

__*Output:*__ *date* and number of *icu_patients + hosp_patients* per day

#### **SHUFFLING PHASE**


__*Input:*__ output of the map function

__*Output:*__ each *date* appear with a list of all of its *icu_patients + hosp_patients*

#### **REDUCE PHASE**

__*Input:*__ output of the *shuffling phase*

__*Output:*__ sum of *icu_patients + hosp_patients* for each *date* 

#### **FINAL RESULT**

The final result is composed by a *key-value* pair made of **KEY** = date, **VALUE** = number of *icu_patients + hosp_patients* for that date.

![immagine](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/7379e18b-c2f7-4875-b9b7-0ffbaa2f7a54)



### Job 3 results:
The execution of this job has been done in all 3 Hadoop configurations (*Local and Pseudo-distributed, __with__ and __without__ __YARN__*). 

*Three different types of input data* have been used. Results changes with respect to the inputs:

#### **Input: MARCH-APRIL data**
![immagine](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/d05cda43-a675-4dab-8fa7-cd50e2e305c0)

#### **Input: MARCH-AUGUST data**
![immagine](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/074b8854-fad7-405b-a5ce-e64111af6d81)


#### **Input: MARCH-OCTOBER data**
![immagine](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/a1224fe4-85ff-485e-81a9-f3606e0e82ed)



## Hadoop configurations time comparison
For every job some tabular and graphical comparison of job's execution times in *local* and *pseudo-distributed* configurations *with and without YARN* have been computed. Obviously all these execution times are taken even considering the *different input sizes* used.

![immagine](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/ade0f1e9-3d60-43a9-a516-8d72766d7f7d)

![a](https://github.com/d-elicio/COVID-19-MapReduce-project/assets/96207365/0d6f8529-69e4-454b-b18e-ecc6b30936ee)


### Time comparison results discussion
- As we expect, the fastest Hadoop configuration is the **Local** (or *standalone mode*) and this because in this configuration Hadoop runs *in a single JVM* and uses the *local filesystem* instead of the Hadoop Distributed Fyle System (HDFS).  Furthermore in this configuration the job will be run with *one* mapper and *one* reducer and this guarantees a good speed (not true for too large input data) and it is why Local configuration is used particularly for the code *debugging* and *testing*.

- On the other way **Pseudo-distributed** mode is used to simulate the possible behavior of distributed computation, but it’s done by running *Hadoop daemons in different JVM instances on a single machine*. This configuration uses **HDFS** instead of the local filesystem and there can be *multiple* mappers and *multiple* reducers to run the job and this increments the execution times.

- These times really increase when the job is ran in **Pseudo-distributed mode using YARN** because the map and reduce times sum up to the times due to *resource managing*.



## Support
For any support, error corrections, etc. please email me at domenico.elicio13@gmail.com
