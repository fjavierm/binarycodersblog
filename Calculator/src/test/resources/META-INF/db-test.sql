-- operationCached
insert into operationCached (id, checkSum, operationResult) values (1, 'aaaa', 10);
insert into operationCached (id, checkSum, operationResult) values (2, 'bbbb', 20);
insert into operationCached (id, checkSum, operationResult) values (3, 'cccc', 30);
insert into operationCached (id, checkSum, operationResult) values (4, 'dddd', 40);
insert into operationCached (id, checkSum, operationResult) values (5, 'eeee', 50);

insert into dbsequence (id, lastValue) values ('idopcached', 6);