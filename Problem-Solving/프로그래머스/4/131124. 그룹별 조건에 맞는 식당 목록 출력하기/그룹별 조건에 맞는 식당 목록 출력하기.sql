-- 코드를 입력하세요
SELECT 
    p.MEMBER_NAME, 
    r.REVIEW_TEXT, 
    DATE_FORMAT(r.REVIEW_DATE, '%Y-%m-%d') as REVIEW_DATE
FROM 
    MEMBER_PROFILE p
JOIN 
    REST_REVIEW r
ON 
    r.MEMBER_ID = p.MEMBER_ID
WHERE 
    r.member_ID = (
        SELECT 
            MEMBER_ID
        FROM 
            REST_REVIEW
        GROUP BY 
            MEMBER_ID
        ORDER BY 
            COUNT(*) DESC
        LIMIT 1
)
ORDER BY 
    r.REVIEW_DATE ASC, r.REVIEW_TEXT ASC;