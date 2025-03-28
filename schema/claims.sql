DROP TABLE IF EXISTS public.claims;

DROP TABLE IF EXISTS public.claim_statuses;

DROP SEQUENCE IF EXISTS public.claims_id_seq;

DROP SEQUENCE IF EXISTS public.claim_statuses_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.claim_statuses_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.claim_statuses
(
    id bigint NOT NULL DEFAULT nextval('claim_statuses_id_seq'::regclass),
    code character varying(50) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    active boolean DEFAULT true,
    CONSTRAINT claim_statuses_pkey PRIMARY KEY (id),
    CONSTRAINT claim_statuses_code_key UNIQUE (code)
);

ALTER SEQUENCE public.claim_statuses_id_seq
    OWNED BY public.claim_statuses.id;

ALTER SEQUENCE public.claim_statuses_id_seq
    OWNER TO postgres;

INSERT INTO claim_statuses (code, description) VALUES
    ('NEW', 'New Claim'),
    ('IN_PROGRESS', 'Claim Under Review'),
    ('COMPLETED', 'Claim Completed'),
    ('REJECTED', 'Claim Rejected'),
    ('CANCELLED', 'Claim Cancelled'),
    ('APPROVED', 'Claim Approved'),
    ('REJECTED', 'Claim Rejected');

CREATE SEQUENCE IF NOT EXISTS public.claims_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.claims
(
    id integer NOT NULL DEFAULT nextval('claims_id_seq'::regclass),
    status_id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    amount numeric(10,2) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    updated_at timestamp without time zone,
    CONSTRAINT claims_pkey PRIMARY KEY (id),
    CONSTRAINT claims_status_id_fkey FOREIGN KEY (status_id)
        REFERENCES public.claim_statuses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER SEQUENCE public.claims_id_seq
    OWNED BY public.claims.id;

ALTER SEQUENCE public.claims_id_seq
    OWNER TO postgres;

INSERT INTO claims (name, description, amount, status_id, created_at, updated_at, created_by)
VALUES 
    ('Car Accident Claim', 'Front bumper damage from collision', 2500.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system'),
    ('Home Insurance Claim', 'Water damage from burst pipe', 5000.00, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system'),
    ('Medical Claim', 'Emergency room visit', 1200.00, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system'),
    ('Property Damage', 'Storm damage to roof', 8000.00, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system'),
    ('Theft Claim', 'Stolen electronics', 3500.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system'),
    ('Liability Claim', 'Slip and fall incident', 1500.00, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system'),
    ('Auto Glass Claim', 'Windshield replacement', 800.00, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system'),
    ('Flood Damage', 'Basement flooding', 12000.00, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system'),
    ('Personal Injury', 'Sports injury', 3000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system'),
    ('Business Interruption', 'Loss of income due to fire', 15000.00, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system');