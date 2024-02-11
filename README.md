# Easy Rider bus company
Bus schedule mistake finder. A nice practice of working with JSON and itertools, based on HyperSkill learning project.

## JSON Database specification
| Field name | Description              | Type      | Format                                                                                                | Other    |
|------------|--------------------------|-----------|-------------------------------------------------------------------------------------------------------|----------|
| bus_id     | Name of the bus line     | Integer   |                                                                                                       | Required |
| stop_id    | ID of stop               | Integer   |                                                                                                       | Required |
| stop_name  | Name of the current stop | String    | [proper name] [suffix] Suffix: Road/Avenue/Boulevard/Street Proper name starts with a capital letter. | Required |
| next_stop  | ID of the next stop      | Integer   |                                                                                                       | Required |
| stop_type  | Stop type                | Character | S (for starting stop) O (for stop on demand) F (for final stop)                                       |          |
| a_time     | Arrive time              | String    | HH:MM (24 hour date format)                                                                           | Required |

## Supported languages:
- Go
- Java
- Python
