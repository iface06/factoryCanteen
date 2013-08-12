'use strict';

describe('Find Menu Test', function() {

    beforeEach(function() {
        browser().navigateTo('index.html');
    }); 

    it('menu for calendar week found', function() {
        
        expect(binding('menu.calendarWeek.week')).toBe("32");
        expect(binding('menu.calendarWeek.year')).toBe("2013");
    });
});
