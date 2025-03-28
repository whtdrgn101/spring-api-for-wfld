-- Remove foreign key constraint
ALTER TABLE claims 
    DROP CONSTRAINT IF EXISTS claims_status_id_fkey;

-- Remove status_id column from claims
ALTER TABLE claims 
    DROP COLUMN IF EXISTS status_id;

-- Drop claim_statuses table
DROP TABLE IF EXISTS claim_statuses; 