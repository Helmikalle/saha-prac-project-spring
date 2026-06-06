# Backend API Examples

Base URL for local development:

```text
http://localhost:8081
```

The default local profile uses H2. The current React frontend expects this backend to run on port `8081`.

## Local H2 Seed Data

The `h2` profile seeds sample development content at startup when the database is empty.

Seeded frontend-facing records:

- `GET /text`
- `GET /property/sauna1`
- `GET /property/venevaja1`

The seeded image URLs use placeholder external images so the frontend can render a working image flow immediately. Replace them with real saw-complex photo URLs when available.

## Frontend Compatibility Notes

The current React frontend calls:

- `GET /text`
- `GET /property/sauna1`
- `GET /property/venevaja1`

The frontend currently expects image URLs in the field `sahaPhotoURL`. The backend now also supports the generic field `imageUrl`.

Preferred new field:

```json
{
  "imageUrl": "https://photos.example/sauna-front.jpg"
}
```

Temporary compatibility field:

```json
{
  "sahaPhotoURL": "https://photos.example/sauna-front.jpg"
}
```

Both fields currently fall back to each other in API responses. This keeps the existing frontend working while allowing the frontend to migrate to `imageUrl`.

## Text Content

### Get All Texts

```http
GET /text
```

Example response:

```json
[
  {
    "id": 1,
    "paragraph": "Welcome to the saw complex.",
    "type": "intro"
  }
]
```

### Create Text

```http
POST /text
Content-Type: application/json
```

Example request:

```json
{
  "paragraph": "Welcome to the saw complex.",
  "type": "intro"
}
```

Current behavior:

- Returns `201 Created`.
- Returns a `Location` header like `http://localhost:8081/text/1`.
- Response body is currently empty.

## Property Content

### Get All Properties

```http
GET /property
```

Example response:

```json
[
  {
    "id": 1,
    "propertyId": "sauna1",
    "paragraph": "The sauna building sits near the old saw complex.",
    "imageList": [
      {
        "id": 10,
        "name": "Sauna front view",
        "imageUrl": "https://photos.example/sauna-front.jpg",
        "sahaPhotoURL": "https://photos.example/sauna-front.jpg",
        "thumbnailUrl": "https://photos.example/sauna-front-thumb.jpg",
        "caption": "Front side of the sauna building.",
        "altText": "Old wooden sauna building seen from the front.",
        "sortOrder": 1,
        "source": "google-photos",
        "propertyId": "sauna1"
      }
    ]
  }
]
```

When the H2 database is empty, this endpoint returns:

```json
[]
```

### Get Property By Property ID

```http
GET /property/{propertyId}
```

Frontend examples:

```http
GET /property/sauna1
GET /property/venevaja1
```

Example response:

```json
{
  "id": 1,
  "propertyId": "sauna1",
  "paragraph": "The sauna building sits near the old saw complex.",
  "imageList": [
    {
      "id": 10,
      "name": "Sauna front view",
      "imageUrl": "https://photos.example/sauna-front.jpg",
      "sahaPhotoURL": "https://photos.example/sauna-front.jpg",
      "thumbnailUrl": "https://photos.example/sauna-front-thumb.jpg",
      "caption": "Front side of the sauna building.",
      "altText": "Old wooden sauna building seen from the front.",
      "sortOrder": 1,
      "source": "google-photos",
      "propertyId": "sauna1"
    }
  ]
}
```

When no property is found, this endpoint returns `404 Not Found`.

### Create Property

```http
POST /property
Content-Type: application/json
```

Example request:

```json
{
  "propertyId": "sauna1",
  "paragraph": "The sauna building sits near the old saw complex."
}
```

Current behavior:

- Returns `201 Created`.
- Returns a `Location` header like `http://localhost:8081/property/1`.
- Response body is currently empty.

## Image Content

### Create Image

```http
POST /image
Content-Type: application/json
```

Example request:

```json
{
  "name": "Sauna front view",
  "imageUrl": "https://photos.example/sauna-front.jpg",
  "thumbnailUrl": "https://photos.example/sauna-front-thumb.jpg",
  "caption": "Front side of the sauna building.",
  "altText": "Old wooden sauna building seen from the front.",
  "sortOrder": 1,
  "source": "google-photos",
  "propertyId": "sauna1"
}
```

Compatibility request using the old field:

```json
{
  "name": "Sauna front view",
  "sahaPhotoURL": "https://photos.example/sauna-front.jpg",
  "propertyId": "sauna1"
}
```

Current behavior:

- Returns `201 Created`.
- Returns a `Location` header like `http://localhost:8081/image/10`.
- Response body is currently empty.
- If only `imageUrl` is provided, responses still include `sahaPhotoURL` with the same value for frontend compatibility.
- If only `sahaPhotoURL` is provided, responses still include `imageUrl` with the same value for migration compatibility.

### Delete Image

```http
DELETE /image/{id}
```

Example:

```http
DELETE /image/10
```

Current behavior:

- Returns `204 No Content` when deletion succeeds.
- Returns `404 Not Found` when no image exists for the given id.

## Delete Property

```http
DELETE /property/{propertyId}
```

Example:

```http
DELETE /property/sauna1
```

Current behavior:

- Deletes the property found by `propertyId`.
- Because `PropertyContent.imageList` uses cascade delete, linked image rows may also be deleted.
- Returns `204 No Content` when deletion succeeds.
- Returns `404 Not Found` when no property exists for the given `propertyId`.
