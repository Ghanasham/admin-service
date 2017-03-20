# admin-service
Service for organization admins to manage their org and people in application workflow system

Rest End points

Organization
GET /admin/org/{orgId}
PUT /admin/org/{orgId}

Manager
GET /admin/manager/{employeeId}/{appType}
GET /next-manager/{orgId}/{appType}/{level}
PUT GET /admin/manager/{employeeId}/{appType}
DELETE /admin/manager/{employeeId}/{appType}
