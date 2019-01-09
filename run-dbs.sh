 docker run --rm -d -p 5432:5432 --name authdb postgres
 docker run --rm -d -p 5433:5432 --name centersdb postgres
 docker run --rm -d -p 5434:5432 --name jobsdb postgres
 docker run --rm -d -p 5435:5432 --name seekersdb postgres
