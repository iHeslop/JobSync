# JobSync

## Summary

You task is to build a Resourcing API using the Java Spring Boot framework, that allows consumers to assign temps to jobs.

## Endpoints

- `POST /jobs` - Creates a job - DONE
- `PATCH /jobs/{id}` - Updates job, endpoint should be used to assign temps to jobs - DONE
- `GET /jobs` - Fetch all jobs - DONE
- `GET /jobs?assigned={true|false}` - Filter by whether a job is assigned to a temp or not
- `GET /jobs/{id}` - (id, name, tempId, startDate, endDate) - DONE


- `POST /temps` - Create a temp - DONE
- `GET /temps` - List all temps - DONE
- `GET /temps?jobId={jobId}` - List temps that are available for a job based on the jobs date range 
- `GET /temps/{id}` - get temp by id (should also display jobs they've been assigned to) - DONE

## Payloads

```js
// GET /jobs/{id}
{
	id: ...,
	name: ...,
	startDate: ...,
	endDate: ...,
	temp: {
		id: ...,
		firstName: ...,
		lastName: ...,
	} // temp can also be null if a temp hasn't been assigned to the job
}

// GET /temps/{id}
{
	id: ...,
	firstName: ...,
	lastName: ...,
	jobs: [{
		id: ...,
		name: ...,
		startDate: ...,
		endDate: ...,
	}, ...] // can be empty if temp hasn't been assigned to jobs
}
```

## Assumptions

- Temps can only have one job at a time (can't be doing 2 jobs on the same date)
- Temps can have many jobs, and job can have 1 temp assigned
- Should be able to assign existing temps to jobs via the POST /jobs & PATCH /jobs/{id}
- You must use a relational database.

# Part 2:

## Pre-requisites

- [Resourcing API Project](../postcode-api/) is required first
- You will need to add spring security to your backends to allow temps to log in (you'll need to add credentials to each temp record too)
- API should only return jobs that are unassigned or are assigned to the reports of the currently logged in temp
- API should only return temps that are reporting to the currently logged-in user in the hierarchy

## Assumptions

- All endpoints need to be authorized, apart from login
- All pages other than `/login` should redirect to `/login` if no temp is logged in
- Currently logged-in users should not be able to see or update temps or jobs assigned to temps that are not reporting to them in the hierarchy

## Stack

- Vite, styled-components, Typescript, React
- Make sure to use proper theming in your application via styled-components
- Make sure to decouple state and styling

## Pages

`/login`

- Allows user to log in
- Each temp should be allowed to log in
- You will need to add credentials and spring security to your spring applications

`/profile`

- Display the current logged in user
- Should be able to edit all fields

`/temps`

- Display all temps, each entry should link to `/temps/:id`
- You should only be able to see temps that report to you (logged in temp)

`/temps/:id`

- Display temp record by `id`
- You should only be able to see temps that report to you (logged in temp)
- Attempting to get a temp that doesn't report to the currently logged-in user should return a 404 page

`/jobs`

- Display all unassigned jobs
- Display jobs that are assigned either to the currently logged in temp, or reports of the currently logged in temp

`/jobs/:id`
Display job information, with a drop down to assign temps
Dropdown should be populated by the API
