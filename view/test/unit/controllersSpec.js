'use strict';

describe('Buzz Client', function() {

    beforeEach(function() {

        browser().navigateTo('http://localhost:8182/web/index.html');
    });

    it('should filter results', function() {
        expect(binding('menu.calendarWeek.week')).toBe(Date.today().getWeek);
        expect(binding('menu.calendarWeek.year')).toBe(Date.today().getYear);
    });
});
