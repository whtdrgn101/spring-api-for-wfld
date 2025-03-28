-- Create claim_statuses table
CREATE TABLE IF NOT EXISTS claim_statuses (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Insert initial claim statuses
INSERT INTO claim_statuses (code, description) VALUES
    ('NEW', 'New Claim'),
    ('IN_PROGRESS', 'Claim Under Review'),
    ('COMPLETED', 'Claim Completed'),
    ('REJECTED', 'Claim Rejected'),
    ('PENDING_INFO', 'Pending Additional Information'),
    ('APPROVED', 'Claim Approved'),
    ('CLOSED', 'Claim Closed');

-- Add foreign key to claims table
ALTER TABLE claims 
    ADD COLUMN IF NOT EXISTS status_id BIGINT REFERENCES claim_statuses(id);

-- Update existing claims to have a default status (NEW)
UPDATE claims 
SET status_id = (SELECT id FROM claim_statuses WHERE code = 'NEW')
WHERE status_id IS NULL;

-- Make status_id NOT NULL after setting defaults
ALTER TABLE claims 
    ALTER COLUMN status_id SET NOT NULL; 