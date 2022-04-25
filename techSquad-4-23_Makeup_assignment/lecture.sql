-- ORDERING RESULTS

-- Populations of all states from largest to smallest.

Select population from state order by population desc;

-- States sorted alphabetically (A-Z) within their census region. The census regions are sorted in reverse alphabetical (Z-A) order.
select state_name, census_region from state order by census_region desc;


-- The biggest park by area

select area,park_name from park order by area desc limit 1;


-- LIMITING RESULTS

-- The 10 largest cities by populations


select population, city_name from city order by population desc limit 10; 


-- The 20 oldest parks from oldest to youngest in years, sorted alphabetically by name.

select park_name, date_established from park
order by date_established asc, park_name
limit 20;



-- CONCATENATING OUTPUTS

-- All city names and their state abbreviation.

select city_name, state_abbreviation from city;



-- The all parks by name and date established.

select park_name, date_established from park;


-- The census region and state name of all states in the West & Midwest sorted in ascending order.

select census_region, state_name from state
where census_region ='West' or census_region ='Midwest'
order by census_region asc;

-- AGGREGATE FUNCTIONS

-- Average population across all the states. Note the use of alias, common with aggregated values.

select avg(population) as average_population, state_abbreviation from city
group by state_abbreviation order by average_population desc;




-- Total population in the West and South census regions

select sum(population) as total_population, census_region
from state 
where census_region = 'West' or census_region='South'
group by census_region;



-- The number of cities with populations greater than 1 million

select count(city_name) from city where population > 1000000;
--select city_name, population from city where population >1000000 order by population DESC;

-- The number of state nicknames.

select count(state_nickname) from state ;


-- The area of the smallest and largest parks.



select area as Smallest_and_largest_area, park_name 
from park
where area = (select min(area) from park)
union 
select area , park_name
from park
where area = (select max(area) from park);




-- GROUP BY

-- Count the number of cities in each state, ordered from most cities to least.

select state_abbreviation, count(city_name) as number_of_citites from city
group by state_abbreviation order by number_of_citites desc;


-- Determine the average park area depending upon whether parks allow camping or not.
select avg(area), has_camping
from park
group by has_camping;



-- Sum of the population of cities in each state ordered by state abbreviation.

select sum(population), state_abbreviation from city group by state_abbreviation order by state_abbreviation;


-- The smallest city population in each state ordered by city population.

select min(city.population) as smallest_population_city, state_abbreviation
from city
group by state_abbreviation
order by smallest_population_city asc;


-- Miscelleneous

-- While you can use LIMIT to limit the number of results returned by a query,
-- it's recommended to use OFFSET and FETCH if you want to get
-- "pages" of results.
-- For instance, to get the first 10 rows in the city table
-- ordered by the name, you could use the following query.
-- (Skip 0 rows, and return only the first 10 rows from the sorted result set.)



-- SUBQUERIES (optional)

-- Include state name rather than the state abbreviation while counting the number of cities in each state,

SELECT count(city_name), state_name
FROM city, state 
WHERE city.state_abbreviation = state.state_abbreviation
group by state.state_name;

--select count(city_name) from city group by state_abbreviation order by state_abbreviation
--join state_name from state on state.state_abbreviation=city.state_abbreviation;


-- Include the names of the smallest and largest parks

select area as Smallest_and_largest_area, park_name 
from park
where area = (select min(area) from park)
union 
select area , park_name
from park
where area = (select max(area) from park);


-- List the capital cities for the states in the Northeast census region.

select city_name, state_name
from city, state
where city_id = state.capital 
and state.census_region= 'Northeast';
