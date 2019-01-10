docker build -t applications -f applications\applications.Dockerfile applications
docker build -t auth -f auth\auth.Dockerfile auth
docker build -t eurekaregistry -f eurekaregistry\eurekaregistry.Dockerfile eurekaregistry
docker build -t gateway -f gateway\gateway.Dockerfile gateway
docker build -t jobcenters -f jobcenter\jobcenter.Dockerfile jobcenter
docker build -t jobs -f jobs\jobs.Dockerfile jobs
REM docker build -t notification -f notification\notification.Dockerfile notification
docker build -t search -f search\search.Dockerfile search
docker build -t seekers -f seekers\seekers.Dockerfile seekers
