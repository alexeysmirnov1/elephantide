<?php

namespace App\Models;

use Support\Logger;
use Spatie\Media\HasMedia;
use Serializable;

class User extends Model implements HasMedia, Serializable
{
    private string $name = 'John Smite'; 
    string $last_name = '123';

    protected function name(string $mode = '33', $x = 3, $y): string
    {
        sort([7,5.5,9]);
        $this->func();

        $db->select()
            ->get();

        $surname = DB::table('users')->first()->surname;
        $this->last_name = $surname;

        return $this->name;
    }
}
 
