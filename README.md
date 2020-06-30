# good-service API

##GET /goods - return list of all goods

Returns HTTP status codes
* `200 OK`
* `500 Internal service error`


##GET /goods/{id} - return the good
`id` - good id

Returns HTTP status codes
* `200 OK`
* `404 No such element`
* `500 Internal service error`

##POST /goods - create a new good and return this one with id
###Request body example
```json
{
    "amount": 8,
    "description": "Pepsi"
}
```

Returns HTTP status codes
* `200 OK`
* `400 Bad request`
* `409 Conflict`
* `500 Internal service error`

##PUT /goods/{id} - update the good and return updated one
`id` - good id
###Request body example
```json
{
    "id": 2,
    "amount": 8,
    "description": "Pepsi"
}
```

Returns HTTP status codes
* `200 OK`
* `400 Bad request`
* `404 Not found`
* `409 Conflict`
* `500 Internal service error`

##DELETE /goods/{id} - remove the good
`id` - good id
###Request body example
```json
{
    "id": 2,
    "amount": 8,
    "description": "Pepsi"
}
```

Returns HTTP status codes
* `204 No content`
* `400 Bad request`
* `500 Internal service error`