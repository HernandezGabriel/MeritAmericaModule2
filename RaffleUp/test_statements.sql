select * from raffle join users on raffle.raffle_owner_id = users.users_id;

select * from ticket join users on ticket.ticket_users_id = users.users_id
join raffle on ticket.ticket_raffle_id = raffle.raffle_id